package by.bsuir.spp.service;

import by.bsuir.spp.exception.service.ServiceException;

import java.io.Serializable;

public interface GenericService<K, T extends Serializable> {
    T execute(K obj) throws ServiceException;
}
