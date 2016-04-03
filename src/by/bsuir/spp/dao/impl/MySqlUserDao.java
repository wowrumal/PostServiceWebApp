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
    private static final String INSERT_USER_QUERY = "insert into `user` (login, `password`, firstName, middleName, secondName, passportID, email, phone) " +
                                                    "values (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String DELETE_USER_BY_ID = "delete from `user` where id=?";
    private static final String SELECT_ALL_USERS = "select * from `user`";
    private static final String UPDATE_USER_BY_ID = "update `user` set login=?, firstName=?, middleName=?, secondName=?, userType=?, email=?, phone=? "+
                                                    "where id=?";
    private static final String SELECT_IDS = "select id FROM `user`";
    private static final String SQL_CHECK_USER = "select * FROM user WHERE login = ? AND `password` =?";
    private static final String SQL_CHECK_LOGIN = "select * FROM user WHERE login = ?";

    @Override
    public Integer create(User newInstance) throws DaoException {
        int id = -1;

        try(Connection connection = ConnectionPoolImpl.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_USER_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, newInstance.getLogin());
            statement.setString(2, newInstance.getPassword());
            statement.setString(3, newInstance.getFirstName());
            statement.setString(4, newInstance.getMiddleName());
            statement.setString(5, newInstance.getSecondName());
            statement.setInt(6, newInstance.getPassport().getPassportId());
            statement.setString(7, newInstance.getEmail());
            statement.setString(8, newInstance.getPhone());
            statement.execute();
            try(ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    id = resultSet.getInt(1);
                }
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Create user error", e);
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
                    user.setUserRole(UserType.values()[resultSet.getInt(8)]);
                    user.setEmail(resultSet.getString(9));
                    user.setPhone(resultSet.getString(10));
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
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getMiddleName());
            statement.setString(4, user.getSecondName());
            statement.setInt(5, user.getUserRole().ordinal());
            statement.setString(6, user.getEmail());
            statement.setString(7, user.getPhone());
            statement.setInt(8, user.getId());
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
                user.setUserRole(UserType.values()[resultSet.getInt(8)]);
                user.setEmail(resultSet.getString(9));
                user.setPhone(resultSet.getString(10));
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public List<Integer> getUserIds() {
        List<Integer> ids = new ArrayList<>();
        try (Connection connection = ConnectionPoolImpl.getInstance().getConnection();
            java.sql.Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_IDS)) {
            while (resultSet.next()) {
                ids.add(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }

        return ids;
    }

    @Override
    public User checkUser(User user) {
        User actualUser = null;
        try(Connection connection = ConnectionPoolImpl.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_CHECK_USER);) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());

            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    actualUser = new User();
                    actualUser.setId(resultSet.getInt(1));
                    actualUser.setFirstName(resultSet.getString(4));
                    actualUser.setMiddleName(resultSet.getString(5));
                    actualUser.setSecondName(resultSet.getString(6));

                    Passport passport = new Passport();
                    passport.setPassportId(resultSet.getInt(7));
                    actualUser.setPassport(passport);
                    actualUser.setUserRole(UserType.values()[resultSet.getInt(8)]);
                    actualUser.setEmail(resultSet.getString(9));
                    actualUser.setPhone(resultSet.getString(10));
                }
            }
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
        }

        return actualUser;
    }

    @Override
    public boolean checkLogin(String login) {
        try(Connection connection = ConnectionPoolImpl.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_CHECK_LOGIN);) {
            statement.setString(1, login);
            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return false;
                }
            }
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
        }
        return true;
    }
}
