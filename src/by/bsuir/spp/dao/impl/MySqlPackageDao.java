package by.bsuir.spp.dao.impl;

import by.bsuir.spp.bean.document.Package;
import by.bsuir.spp.dao.PackageDao;
import by.bsuir.spp.dao.connectionpool.impl.ConnectionPoolImpl;
import by.bsuir.spp.exception.dao.DaoException;
import by.bsuir.spp.exception.dao.connectionpool.ConnectionPoolException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Кирилл on 2/15/2016.
 */
public class MySqlPackageDao implements PackageDao {

    private static final MySqlPackageDao instance = new MySqlPackageDao();

    private MySqlPackageDao(){}

    public static MySqlPackageDao getInstance() { return instance;}

    private static final String INSERT_PACKAGE_QUERY = "insert into `package` (type, `date`, senderName, getterName, address, postIndex, barcode) "+
                                                        "values (?,?,?,?,?,?,?)";
    private static final String SELECT_ALL_PACKAGE = "select * from `package`";
    private static final String SELECT_PACKAGE_BY_ID = "select * from `package` where id=?";
    private static final String DELETE_PACKAGE_BY_ID = "delete from `package` where id=?";
    private static final String UPDATE_PACKAGE_BY_ID = "update `package` set type=?, date=?, senderName=?, getterName=?, address=?, postIndex=?, barcode=? "+
                                                        "where id=?";

    private static final String SELECT_PACKAGE_IDS = "select id from package";

    @Override
    public Integer create(by.bsuir.spp.bean.document.Package newInstance) throws DaoException {
        int id = 0;

        try(Connection connection =  ConnectionPoolImpl.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_PACKAGE_QUERY, Statement.RETURN_GENERATED_KEYS))
        {
            statement.setString(1, newInstance.getType());
            statement.setDate(2, new Date(newInstance.getDate().getTime()));
            statement.setString(3, newInstance.getSenderName());
            statement.setString(4, newInstance.getGetterName());
            statement.setString(5, newInstance.getAddress());
            statement.setInt(6, newInstance.getPostIndex());
            statement.setInt(7, newInstance.getBarCode());
            statement.execute();

            try(ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next())
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
    public Package read(Integer id) throws DaoException {
        Package myPackage = null;

        try(Connection connection = ConnectionPoolImpl.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_PACKAGE_BY_ID))
        {
            statement.setInt(1, id);
            try(ResultSet resultSet = statement.executeQuery())
            {
                if (resultSet.next())
                {
                    myPackage = new Package();
                    myPackage.setIdPackage(id);
                    myPackage.setType(resultSet.getString(2));
                    myPackage.setDate(resultSet.getDate(3));
                    myPackage.setSenderName(resultSet.getString(4));
                    myPackage.setGetterName(resultSet.getString(5));
                    myPackage.setAddress(resultSet.getString(6));
                    myPackage.setPostIndex(resultSet.getInt(7));
                    myPackage.setBarCode(resultSet.getInt(8));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }

        return myPackage;
    }

    @Override
    public void update(Package obj) throws DaoException {
        try(Connection connection = ConnectionPoolImpl.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_PACKAGE_BY_ID)) {
            statement.setString(1, obj.getType());
            statement.setDate(2, new Date(obj.getDate().getTime()));
            statement.setString(3, obj.getSenderName());
            statement.setString(4, obj.getGetterName());
            statement.setString(5, obj.getAddress());
            statement.setInt(6, obj.getPostIndex());
            statement.setInt(7, obj.getBarCode());
            statement.setInt(8, obj.getIdPackage());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Package obj) throws DaoException {
        try(Connection connection = ConnectionPoolImpl.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_PACKAGE_BY_ID)){
            statement.setInt(1, obj.getIdPackage());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Package> getAllPackages() {
        List<Package> packages = new ArrayList<>();

        try(Connection connection = ConnectionPoolImpl.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_PACKAGE);
            ResultSet resultSet = statement.executeQuery()){
            while (resultSet.next())
            {
                Package myPackage = new Package();
                myPackage.setIdPackage(resultSet.getInt(1));
                myPackage.setType(resultSet.getString(2));
                myPackage.setDate(resultSet.getDate(3));
                myPackage.setSenderName(resultSet.getString(4));
                myPackage.setGetterName(resultSet.getString(5));
                myPackage.setAddress(resultSet.getString(6));
                myPackage.setPostIndex(resultSet.getInt(7));
                myPackage.setBarCode(resultSet.getInt(8));

                packages.add(myPackage);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
        return packages;
    }

    @Override
    public List<Integer> getPackageIds() {
        List<Integer> ids = new ArrayList<>();

        try(Connection connection = ConnectionPoolImpl.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_PACKAGE_IDS)) {
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
}
