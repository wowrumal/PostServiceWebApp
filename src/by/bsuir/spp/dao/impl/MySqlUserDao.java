package by.bsuir.spp.dao.impl;

import by.bsuir.spp.bean.User;
import by.bsuir.spp.dao.UserDao;
import by.bsuir.spp.exception.dao.DaoException;

public class MySqlUserDao implements UserDao {
    @Override
    public Integer create(User newInstance) throws DaoException {
        return null;
    }

    @Override
    public User read(Integer id) throws DaoException {
        return null;
    }

    @Override
    public void update(User obj) throws DaoException {

    }

    @Override
    public void delete(User obj) throws DaoException {

    }
}
