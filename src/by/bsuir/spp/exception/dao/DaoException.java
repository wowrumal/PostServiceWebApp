package by.bsuir.spp.exception.dao;

import by.bsuir.spp.exception.ApplicationException;

public class DaoException extends ApplicationException {
    public DaoException(String message, Exception e) {
        super(message, e);
    }

    public DaoException(String message) {
        super(message);
    }
}
