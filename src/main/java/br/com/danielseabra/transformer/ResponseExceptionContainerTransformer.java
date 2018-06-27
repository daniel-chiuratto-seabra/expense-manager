package br.com.danielseabra.transformer;

import br.com.danielseabra.exception.ResponseExceptionContainer;
import br.com.danielseabra.exception.inner.InnerExceptionContainer;

public interface ResponseExceptionContainerTransformer {

	ResponseExceptionContainer transform(InnerExceptionContainer innerExceptionContainer, String message);

	ResponseExceptionContainer transformThrowable(Throwable throwable, String message);

}
