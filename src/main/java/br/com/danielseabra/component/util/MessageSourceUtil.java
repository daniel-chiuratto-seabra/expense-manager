package br.com.danielseabra.component.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessageSourceUtil {

	@Autowired
	private MessageSource messageSource;

	public String retrieveMessage(final String messageKey) {
		return this.messageSource.getMessage(messageKey, null, LocaleContextHolder.getLocale());
	}

	public String retrieveMessage(final String messageKey, final String... messagePlaceholderValues) {
		return this.messageSource.getMessage(messageKey, messagePlaceholderValues, LocaleContextHolder.getLocale());
	}

}
