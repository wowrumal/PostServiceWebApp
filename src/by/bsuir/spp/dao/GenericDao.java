package by.bsuir.spp.dao;

import by.bsuir.spp.exception.dao.DaoException;

import java.io.Serializable;

public interface GenericDao <T, PK extends Serializable> {

    PK create(T newInstance) throws DaoException;

    T read(PK id) throws DaoException;

    void update(T obj) throws DaoException;

    void delete(T obj) throws DaoException;

}
