package br.com.danielseabra.exception.inner;

import br.com.danielseabra.common.MessageType;

public interface InnerExceptionContainer {

	default MessageType getMessageType() {
		return MessageType.ERROR;
	}

	String getMessage();

	String[] getDetail();

}
