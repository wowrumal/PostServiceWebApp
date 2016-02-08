package by.bsuir.spp.exception.dao.connectionpool;


import by.bsuir.spp.exception.ApplicationException;

public class ConnectionPoolException extends ApplicationException {
    private static final long serialVersionUID = 1L;


    public ConnectionPoolException(String message, Exception e) {
        super(message, e);
    }

    public ConnectionPoolException(String message) {
        super(message);
    }
}
