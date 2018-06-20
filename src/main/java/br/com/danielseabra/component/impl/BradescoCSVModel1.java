package br.com.danielseabra.component.impl;

import java.util.Collection;
import java.util.Collections;

import org.springframework.stereotype.Component;

import br.com.danielseabra.component.BankProcessor;
import br.com.danielseabra.statement.Statement;

@Component
public class BradescoCSVModel1 extends BankProcessor {

	@Override
	protected Collection<Statement> convert(final Collection<Collection<String>> collection) {
		collection.forEach(l -> System.out.println(l.toString()));
		return Collections.<Statement>emptyList();
	}

}
