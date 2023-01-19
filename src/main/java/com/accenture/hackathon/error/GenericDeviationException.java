package com.accenture.hackathon.error;

public class GenericDeviationException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public GenericDeviationException() {
        super();
    }

    public GenericDeviationException(String message) {
        super(message);
    }

    public GenericDeviationException(String message, Throwable cause) {
        super(message, cause);
    }

    public GenericDeviationException(Throwable cause) {
        super(cause);
    }

    protected GenericDeviationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
