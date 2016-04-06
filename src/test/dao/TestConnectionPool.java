package dao;

import by.bsuir.spp.dao.PassportDao;
import by.bsuir.spp.dao.connectionpool.IConnectionPool;
import by.bsuir.spp.dao.connectionpool.impl.ConnectionPoolImpl;
import by.bsuir.spp.dao.impl.MySqlPassportDao;
import by.bsuir.spp.exception.dao.connectionpool.ConnectionPoolException;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Assert;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;

public class TestConnectionPool extends DBTestCase {

    private static final IConnectionPool connectionPool = ConnectionPoolImpl.getInstance();
    private static PassportDao passportDao = MySqlPassportDao.getInstance();

    public TestConnectionPool(String name) throws ConnectionPoolException {
        super(name);
        System.setProperty(
                PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS,
                "com.mysql.jdbc.Driver");
        System.setProperty(
                PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL,
                "jdbc:mysql://localhost:3307/test_post_service_db?useUnicode=true&characterEncoding=UTF8&autoReconnect=true");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "root");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "123456");
        //connectionPool.init();
    }

    @Override
    protected DatabaseOperation getSetUpOperation() throws Exception {
        connectionPool.init();
        return DatabaseOperation.REFRESH;
    }


    @Override
    protected DatabaseOperation getTearDownOperation() throws Exception {
        connectionPool.dispose();
        return DatabaseOperation.DELETE_ALL;
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/dao/passport_dataset.xml"));
    }

    public void testGetConnection() throws ConnectionPoolException {
        Connection connection = connectionPool.getConnection();
        Assert.assertNotNull(connection);
    }

    public void testGetManyConnections() {
        for (int i = 0; i < 10000; i++) {
            new Thread(){
                @Override
                public void run() {
                    try (Connection connection = connectionPool.getConnection()) {
                        passportDao.getAllPassports();
                    } catch (SQLException | ConnectionPoolException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }
}
