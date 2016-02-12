package by.bsuir.spp.dao.impl;

import by.bsuir.spp.bean.Passport;
import by.bsuir.spp.dao.PassportDao;
import by.bsuir.spp.exception.dao.DaoException;

public class MySqlPassportDao implements PassportDao {

    private static final MySqlPassportDao instance = new MySqlPassportDao();

    private MySqlPassportDao() {}

    public static MySqlPassportDao getInstance() {
        return instance;
    }

    @Override
    public Integer create(Passport newInstance) throws DaoException {
        return null;
    }

    @Override
    public Passport read(Integer id) throws DaoException {
        return null;
    }

    @Override
    public void update(Passport obj) throws DaoException {

    }

    @Override
    public void delete(Passport obj) throws DaoException {

    }
}
