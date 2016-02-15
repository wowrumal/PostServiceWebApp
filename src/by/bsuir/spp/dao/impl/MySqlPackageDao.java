package by.bsuir.spp.dao.impl;

import by.bsuir.spp.bean.document.*;
import by.bsuir.spp.bean.document.Package;
import by.bsuir.spp.dao.PackageDao;
import by.bsuir.spp.dao.connectionpool.impl.ConnectionPoolImpl;
import by.bsuir.spp.exception.dao.DaoException;

import java.sql.*;
import java.util.List;

/**
 * Created by Кирилл on 2/15/2016.
 */
public class MySqlPackageDao implements PackageDao {

    private static final MySqlPackageDao instance = new MySqlPackageDao();

    private MySqlPackageDao(){}

    private static MySqlPackageDao GetInstance() { return instance;}

    private static final String INSERT_PACKAGE_QUERY = "insert into 'package' ('type', 'date', senderName, getterName, address, postIndex, barcode) "+
                                                        "values (?,?,?,?,?,?,?)";
    private static final String SELECT_ALL_PACKAGE = "select * from `package`";
    private static final String SELECT_PACKAGE_BY_ID = "select * from `package` where id=?";
    private static final String DELETE_PACKAGE_BY_ID = "delete from `package` where id=?";
    private static final String UPDATE_PACKAGE_BY_ID = "update `package` set type=?, date=?, senderName=?, getterName=?, address=?, postIndex=?, barcode=? "+
                                                        "where id=?";

    @Override
    public Integer create(by.bsuir.spp.bean.document.Package newInstance) throws DaoException {
        int id = 0;

        try(Connection connection = (Connection) ConnectionPoolImpl.getInstance();
            PreparedStatement statement = connection.prepareStatement(INSERT_PACKAGE_QUERY))
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
        }

        return id;
    }

    @Override
    public Package read(Integer id) throws DaoException {
        return null;
    }

    @Override
    public void update(Package obj) throws DaoException {

    }

    @Override
    public void delete(Package obj) throws DaoException {
        try(Connection connection = (Connection) ConnectionPoolImpl.getInstance();
            PreparedStatement statement = connection.prepareStatement(DELETE_PACKAGE_BY_ID)){
            statement.setInt(1, obj.getIdPackage());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Package> getAllPackages() {
        return null;
    }
}
