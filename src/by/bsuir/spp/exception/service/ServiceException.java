package by.bsuir.spp.exception.service;


import by.bsuir.spp.exception.ApplicationException;

public class ServiceException extends ApplicationException {
    public ServiceException(String message, Exception e) {
        super(message, e);
    }

    public ServiceException(String message) {
        super(message);
    }
}
