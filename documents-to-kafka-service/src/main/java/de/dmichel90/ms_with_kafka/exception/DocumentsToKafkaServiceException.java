package de.dmichel90.exception;

public class DocumentsToKafkaServiceException extends RuntimeException{

    public DocumentsToKafkaServiceException() {
        super();
    }

    public DocumentsToKafkaServiceException(String message) {
        super(message);
    }

    public DocumentsToKafkaServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
