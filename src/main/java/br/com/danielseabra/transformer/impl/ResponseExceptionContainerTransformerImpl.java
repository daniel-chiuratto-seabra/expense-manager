package br.com.danielseabra.transformer.impl;

import org.springframework.stereotype.Component;

import br.com.danielseabra.common.MessageType;
import br.com.danielseabra.exception.ResponseExceptionContainer;
import br.com.danielseabra.exception.impl.ResponseExceptionContainerImpl;
import br.com.danielseabra.exception.inner.InnerExceptionContainer;
import br.com.danielseabra.transformer.ResponseExceptionContainerTransformer;

@Component
public class ResponseExceptionContainerTransformerImpl implements ResponseExceptionContainerTransformer {

	@Override
	public ResponseExceptionContainer transformThrowable(final Throwable throwable, final String message) {
		return new ResponseExceptionContainerImpl(MessageType.ERROR, message, throwable.getMessage());
	}

	@Override
	public ResponseExceptionContainer transform(final InnerExceptionContainer innerExceptionContainer, final String message) {
		return new ResponseExceptionContainerImpl(innerExceptionContainer.getMessageType(), message, innerExceptionContainer.getDetail());
	}

}
