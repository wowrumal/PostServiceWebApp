package by.bsuir.spp.exception;

public class ApplicationException extends Exception {
    private static final long serialVersionUID = 1L;
    private Exception hiddenException;

    public ApplicationException(String message, Exception e) {
        super(message);
        hiddenException = e;
    }

    public ApplicationException(String message) {
        super(message);
    }

    public Exception getHiddenException() {
        return hiddenException;
    }
}
