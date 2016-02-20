package by.bsuir.spp.dao.impl;

import by.bsuir.spp.bean.Passport;
import by.bsuir.spp.bean.User;
import by.bsuir.spp.bean.UserType;
import by.bsuir.spp.dao.UserDao;
import by.bsuir.spp.dao.connectionpool.impl.ConnectionPoolImpl;
import by.bsuir.spp.exception.dao.DaoException;
import by.bsuir.spp.exception.dao.connectionpool.ConnectionPoolException;
import com.mysql.jdbc.Statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlUserDao implements UserDao {

    private static final MySqlUserDao instance = new MySqlUserDao();

    private MySqlUserDao(){}

    public static MySqlUserDao getInstance() {
        return instance;
    }

    private static final String SELECT_USER_BY_ID = "select * from `user` where id=?";
    private static final String INSERT_USER_QUERY = "insert into `user` (login, password, firstName, middleName, secondName, passportID, userType) " +
                                                    "values (?, ?, ?, ?, ?, ?, ?)";
    private static final String DELETE_USER_BY_ID = "delete from `user` where id=?";
    private static final String SELECT_ALL_USERS = "select * from `user`";
    private static final String UPDATE_USER_BY_ID = "update `user` set login=?, password=?, firstName=?, middleName=?, secondName=?, userType=? "+
                                                    "where id=?";

    @Override
    public Integer create(User newInstance) throws DaoException {
        int id = 0;

        try(Connection connection = ConnectionPoolImpl.getInstance().getConnection();
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
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }

        return id;
    }

    @Override
    public User read(Integer id) throws DaoException {
        User user = null;

        try(Connection connection = ConnectionPoolImpl.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_ID)){

            statement.setInt(1, id);
            try(ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next())
                {
                    user = new User();
                    user.setId(id);
                    user.setLogin(resultSet.getString(2));
                    user.setPassword(resultSet.getString(3));
                    user.setFirstName(resultSet.getString(4));
                    user.setMiddleName(resultSet.getString(5));
                    user.setSecondName(resultSet.getString(6));

                    Passport passport = new Passport();
                    passport.setPassportId(resultSet.getInt(7));

                    user.setPassport(passport);
                    user.setUserType(UserType.values()[resultSet.getInt(8)]);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void update(User user) throws DaoException {
        try(Connection connection = ConnectionPoolImpl.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_USER_BY_ID)) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getMiddleName());
            statement.setString(5, user.getSecondName());
            statement.setInt(6, user.getUserType().ordinal());
            statement.setInt(7, user.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(User user) throws DaoException {
        try(Connection connection = ConnectionPoolImpl.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_USER_BY_ID)){

            statement.setInt(1, user.getId());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try(Connection connection = ConnectionPoolImpl.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USERS);
            ResultSet resultSet = statement.executeQuery()){

            while (resultSet.next())
            {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setLogin(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setFirstName(resultSet.getString(4));
                user.setMiddleName(resultSet.getString(5));
                user.setSecondName(resultSet.getString(6));

                Passport passport = new Passport();
                passport.setPassportId(resultSet.getInt(7));

                user.setPassport(passport);
                user.setUserType(UserType.values()[resultSet.getInt(8)]);

                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
        return users;
    }
}
