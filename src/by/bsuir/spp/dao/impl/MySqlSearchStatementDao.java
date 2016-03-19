package by.bsuir.spp.dao.impl;

import by.bsuir.spp.bean.Passport;
import by.bsuir.spp.bean.document.*;
import by.bsuir.spp.bean.document.Package;
import by.bsuir.spp.dao.SearchStatementDao;
import by.bsuir.spp.dao.connectionpool.impl.ConnectionPoolImpl;
import by.bsuir.spp.exception.dao.DaoException;
import by.bsuir.spp.exception.dao.connectionpool.ConnectionPoolException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Кирилл on 2/20/2016.
 */
public class MySqlSearchStatementDao implements SearchStatementDao {

    private static final MySqlSearchStatementDao instance = new MySqlSearchStatementDao();

    private MySqlSearchStatementDao() {}

    public static MySqlSearchStatementDao getInstance() {
        return instance;
    }

    private static final String INSERT_SEARCH_STATEMENT_QUERY = "INSERT into `search_statement` (address, phoneNumber, passportID, petitionContent, packageID, currentDate, managerName) "+
            "values (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_SEARCH_STATEMENT_BY_ID = "select * from `search_statement` where id=?";
    private static final String SELECT_SEARCH_STATEMENT_BY_PASSPORT_ID = "select * from `search_statement` where passportID=?";
    private static final String DELETE_SEARCH_STATEMENT_BY_ID = "delete from `search_statement` where id=?";
    private static final String UPDATE_SEARCH_STATEMENT_BY_ID = "update `search_statement` set address=?, phoneNumber=?, passportID=?, petitionContent=?, packageID=?, currentDate=?, managerName=? "+
            "where id=?";
    private static final String SELECT_ALL_SEARCH_STATEMENT = "select * from `search_statement`";

    @Override
    public List<SearchPackageStatement> getAllSearchStatements() {
        List<SearchPackageStatement> statementList = new ArrayList<>();
        try(Connection connection = ConnectionPoolImpl.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_SEARCH_STATEMENT)) {
            while (resultSet.next()) {
                SearchPackageStatement searchPackageStatement = new SearchPackageStatement();
                searchPackageStatement.setId(resultSet.getInt("id"));
                searchPackageStatement.setAddress(resultSet.getString("address"));
                searchPackageStatement.setPhoneNumber(resultSet.getString("phoneNumber"));
                Passport passport = new Passport();
                passport.setPassportId(resultSet.getInt("passportID"));
                searchPackageStatement.setPassport(passport);
                searchPackageStatement.setPetitionContent(resultSet.getString("petitionContent"));
                by.bsuir.spp.bean.document.Package packagee = new Package();
                packagee.setIdPackage(resultSet.getInt("packageID"));
                searchPackageStatement.setPostPackage(packagee);
                searchPackageStatement.setCurrentDate(resultSet.getDate("currentDate"));
                searchPackageStatement.setPostManagerName(resultSet.getString("managerName"));

                statementList.add(searchPackageStatement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }

        return statementList;
    }

    @Override
    public List<SearchPackageStatement> getSearchStatementByPassportId(int passportId) {
        List<SearchPackageStatement> statementList = new ArrayList<>();
        try(Connection connection = ConnectionPoolImpl.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_SEARCH_STATEMENT_BY_PASSPORT_ID);)
        {
            statement.setInt(1, passportId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    SearchPackageStatement searchPackageStatement = new SearchPackageStatement();
                    searchPackageStatement.setId(resultSet.getInt("id"));
                    searchPackageStatement.setAddress(resultSet.getString("address"));
                    searchPackageStatement.setPhoneNumber(resultSet.getString("phoneNumber"));
                    Passport passport = new Passport();
                    passport.setPassportId(resultSet.getInt("passportID"));
                    searchPackageStatement.setPassport(passport);
                    searchPackageStatement.setPetitionContent(resultSet.getString("petitionContent"));
                    by.bsuir.spp.bean.document.Package packagee = new Package();
                    packagee.setIdPackage(resultSet.getInt("packageID"));
                    searchPackageStatement.setPostPackage(packagee);
                    searchPackageStatement.setCurrentDate(resultSet.getDate("currentDate"));
                    searchPackageStatement.setPostManagerName(resultSet.getString("managerName"));

                    statementList.add(searchPackageStatement);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }

        return statementList;
    }

    @Override
    public Integer create(SearchPackageStatement newInstance) throws DaoException {
        int id = 0;
        try(Connection connection = ConnectionPoolImpl.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_SEARCH_STATEMENT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, newInstance.getAddress());
            statement.setString(2, newInstance.getPhoneNumber());
            statement.setInt(3, newInstance.getPassport().getPassportId());
            statement.setString(4, newInstance.getPetitionContent());
            statement.setInt(5, newInstance.getPostPackage().getIdPackage());
            statement.setDate(6, new Date(newInstance.getCurrentDate().getTime()));
            statement.setString(7, newInstance.getPostManagerName());
            statement.execute();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
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
    public SearchPackageStatement read(Integer id) throws DaoException {
        SearchPackageStatement packageStatement = null;

        try (Connection connection = ConnectionPoolImpl.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_SEARCH_STATEMENT_BY_ID)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    packageStatement = new SearchPackageStatement();
                    packageStatement.setId(id);
                    packageStatement.setAddress(resultSet.getString(2));
                    packageStatement.setPhoneNumber(resultSet.getString(3));
                    Passport passport = new Passport();
                    passport.setPassportId(resultSet.getInt(4));
                    packageStatement.setPassport(passport);
                    packageStatement.setPetitionContent(resultSet.getString(5));
                    Package packagee = new Package();
                    packagee.setIdPackage(resultSet.getInt(6));
                    packageStatement.setPostPackage(packagee);
                    packageStatement.setCurrentDate(resultSet.getDate(7));
                    packageStatement.setPostManagerName(resultSet.getString(8));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }

        return packageStatement;
    }

    @Override
    public void update(SearchPackageStatement obj) throws DaoException {
        try(Connection connection = ConnectionPoolImpl.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_SEARCH_STATEMENT_BY_ID)) {
            statement.setString(1, obj.getAddress());
            statement.setString(2, obj.getPhoneNumber());
            statement.setInt(3, obj.getPassport().getPassportId());
            statement.setString(4, obj.getPetitionContent());
            statement.setInt(5, obj.getPostPackage().getIdPackage());
            statement.setDate(6, new Date(obj.getCurrentDate().getTime()));
            statement.setString(7, obj.getPostManagerName());
            statement.setInt(8, obj.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(SearchPackageStatement obj) throws DaoException {
        try (Connection connection = ConnectionPoolImpl.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_SEARCH_STATEMENT_BY_ID)) {
            statement.setInt(1, obj.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
    }
}
