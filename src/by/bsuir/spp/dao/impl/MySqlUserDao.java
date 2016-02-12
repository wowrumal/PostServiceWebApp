package by.bsuir.spp.dao.impl;

import by.bsuir.spp.bean.User;
import by.bsuir.spp.dao.UserDao;
import by.bsuir.spp.dao.connectionpool.impl.ConnectionPoolImpl;
import by.bsuir.spp.exception.dao.DaoException;
import com.mysql.jdbc.Statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlUserDao implements UserDao {

    private static final MySqlUserDao instance = new MySqlUserDao();

    private MySqlUserDao(){}

    public static MySqlUserDao getInstance() {
        return instance;
    }

    private static final String SELECT_USER_BY_ID = "select * from 'user' where id=?";
    private static final String INSERT_USER_QUERY = "insert into 'user' (login, password, firstName, middleName, secondName, passportID, userType) " +
                                                    "values (?, ?, ?, ?, ?, ?, ?)";

    @Override
    public Integer create(User newInstance) throws DaoException {
        int id = 0;

        try(Connection connection = (Connection) ConnectionPoolImpl.getInstance();
            PreparedStatement statement = connection.prepareStatement(INSERT_USER_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, newInstance.getLogin());
            statement.setString(2, newInstance.getPassword());
            statement.setString(3, newInstance.getFirstName());
            statement.setString(4, newInstance.getMiddleName());
            statement.setString(5, newInstance.getSecondName());
            statement.setInt(6, newInstance.getPassport().getPassportId());
            statement.setInt(7, newInstance.getUserType().ordinal());
            statement.execute();
            try(ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    id = resultSet.getInt(1);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
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
