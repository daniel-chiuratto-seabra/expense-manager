package br.com.danielseabra.component;

import static br.com.danielseabra.common.ApplicationConstants.CSV_SEPARATOR;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.springframework.web.multipart.MultipartFile;

import br.com.danielseabra.statement.Statement;

public abstract class BankProcessor {

	public Collection<Statement> process(final MultipartFile file) {
		try (final InputStream inputStream = file.getInputStream();
			final Reader reader = new InputStreamReader(inputStream);
			final BufferedReader bufferedReader = new BufferedReader(reader)) {
			final Collection<Collection<String>> columnCollectionCollection = new ArrayList<>();
			String line = bufferedReader.readLine();
			while (null != line) {
				final Collection<String> columnCollection = new ArrayList<>();
				final String[] columns = line.split(CSV_SEPARATOR);
				for (final String column : columns)
					columnCollection.add(column);
				columnCollectionCollection.add(columnCollection);
				line = bufferedReader.readLine();
			}

			return this.convert(columnCollectionCollection);
		} catch (final IOException e) {
			e.printStackTrace();
		}

		return Collections.<Statement>emptyList();
	}

	protected abstract Collection<Statement> convert(final Collection<Collection<String>> collection);
}
