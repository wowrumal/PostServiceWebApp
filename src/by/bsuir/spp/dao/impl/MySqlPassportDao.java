package by.bsuir.spp.dao.impl;

import by.bsuir.spp.bean.Passport;
import by.bsuir.spp.dao.PassportDao;
import by.bsuir.spp.dao.connectionpool.impl.ConnectionPoolImpl;
import by.bsuir.spp.exception.dao.DaoException;
import by.bsuir.spp.exception.dao.connectionpool.ConnectionPoolException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlPassportDao implements PassportDao {

    private static final MySqlPassportDao instance = new MySqlPassportDao();

    private MySqlPassportDao() {}

    public static MySqlPassportDao getInstance() {
        return instance;
    }

    private static final String INSERT_PASSPORT_QUERY = "INSERT into 'passport' (passportNumber, address, issuingInsistution, issueDate) "+
                                                        "values (?, ?, ?, ?)";

    @Override
    public Integer create(Passport newInstance) throws DaoException {
        int id = 0;

        try(Connection connection = (Connection) ConnectionPoolImpl.getInstance();
            PreparedStatement statement = connection.prepareStatement(INSERT_PASSPORT_QUERY, java.sql.Statement.RETURN_GENERATED_KEYS))
        {
            statement.setString(1, newInstance.getPassportNumber());
            statement.setString(2, newInstance.getAddress());
            statement.setString(3, newInstance.getIssuingInsitution());
            statement.setDate(4, newInstance.getIssueDate());

            try(ResultSet resultSet = statement.getGeneratedKeys())
            {
                if(resultSet.next())
                {
                    id = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
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
