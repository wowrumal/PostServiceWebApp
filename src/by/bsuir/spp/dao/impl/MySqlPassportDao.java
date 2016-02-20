package by.bsuir.spp.dao.impl;

import by.bsuir.spp.bean.Passport;
import by.bsuir.spp.dao.PassportDao;
import by.bsuir.spp.dao.connectionpool.impl.ConnectionPoolImpl;
import by.bsuir.spp.exception.dao.DaoException;
import by.bsuir.spp.exception.dao.connectionpool.ConnectionPoolException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlPassportDao implements PassportDao {

    private static final MySqlPassportDao instance = new MySqlPassportDao();

    private MySqlPassportDao() {}

    public static MySqlPassportDao getInstance() {
        return instance;
    }

    private static final String INSERT_PASSPORT_QUERY = "INSERT into passport (passportNumber, address, issuingInsistution, issueDate) "+
                                                        "values (?, ?, ?, ?)";
    private static final String SELECT_PASSPORT_BY_ID = "select * from passport where id=?";
    private static final String DELETE_PASSPORT_BY_ID = "delete from passport where id=?";
    private static final String UPDATE_PASSPORT_BY_ID = "update passport set passportNumber=?, address=?, issuingInsistution=?, issueDate=? "+
                                                        "where id=?";
    private static final String SELECT_ALL_PASSPORT = "select * from passport";


    @Override
    public Integer create(Passport newInstance) throws DaoException {
        int id = 0;

        try(Connection connection = ConnectionPoolImpl.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_PASSPORT_QUERY, java.sql.Statement.RETURN_GENERATED_KEYS))
        {
            statement.setString(1, newInstance.getPassportNumber());
            statement.setString(2, newInstance.getAddress());
            statement.setString(3, newInstance.getIssuingInstitution());
            statement.setDate(4, new Date(newInstance.getIssueDate().getTime()));
            statement.execute();

            try(ResultSet resultSet = statement.getGeneratedKeys())
            {
                if(resultSet.next())
                {
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
    public Passport read(Integer id) throws DaoException {
        Passport passport = null;

        try(Connection connection =  ConnectionPoolImpl.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_PASSPORT_BY_ID))
        {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next())
                {
                    passport = new Passport();
                    passport.setPassportId(id);
                    passport.setPassportNumber(resultSet.getString(2));
                    passport.setAddress(resultSet.getString(3));
                    passport.setIssuingInstitution(resultSet.getString(4));
                    passport.setIssueDate(resultSet.getDate(5));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
        return passport;
    }

    @Override
    public void update(Passport passport) throws DaoException {
        try(Connection connection = ConnectionPoolImpl.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_PASSPORT_BY_ID))
        {
            statement.setString(1, passport.getPassportNumber());
            statement.setString(2, passport.getAddress());
            statement.setString(3, passport.getIssuingInstitution());
            statement.setDate(4, new Date(passport.getIssueDate().getTime()));
            statement.setInt(5, passport.getPassportId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Passport passport) throws DaoException {
        try(Connection connection = ConnectionPoolImpl.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_PASSPORT_BY_ID)) {
            statement.setInt(1, passport.getPassportId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Passport> getAllPassports() {
        List<Passport> passports = new ArrayList<>();

        try(Connection connection = ConnectionPoolImpl.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_PASSPORT);
            ResultSet resultSet = statement.executeQuery())
        {
            while (resultSet.next())
            {
                Passport passport = new Passport();
                passport.setPassportId(resultSet.getInt(1));
                passport.setPassportNumber(resultSet.getString(2));
                passport.setAddress(resultSet.getString(3));
                passport.setIssuingInstitution(resultSet.getString(4));
                passport.setIssueDate(resultSet.getDate(5));
                passports.add(passport);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }

        return passports;
    }
}
