package dao;

import by.bsuir.spp.bean.Passport;
import by.bsuir.spp.bean.document.Package;
import by.bsuir.spp.bean.document.SearchPackageStatement;
import by.bsuir.spp.dao.SearchStatementDao;
import by.bsuir.spp.dao.connectionpool.IConnectionPool;
import by.bsuir.spp.dao.connectionpool.impl.ConnectionPoolImpl;
import by.bsuir.spp.dao.impl.MySqlSearchStatementDao;
import by.bsuir.spp.exception.dao.DaoException;
import by.bsuir.spp.exception.dao.connectionpool.ConnectionPoolException;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Assert;

import java.io.FileInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestSearchStatementDao extends DBTestCase {
    private static SearchStatementDao searchStatementDao = MySqlSearchStatementDao.getInstance();

    private static final IConnectionPool connectionPool = ConnectionPoolImpl.getInstance();
    private IDataSet[] dataSets;

    public TestSearchStatementDao(String name) throws ConnectionPoolException {
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
        dataSets = new IDataSet[] {
                new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/dao/passport_dataset.xml")),
                new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/dao/user_dataset.xml")),
                new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/dao/package_dataset.xml")),
                new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/dao/search_statement_dataset.xml"))
        };

        return new CompositeDataSet(dataSets);
    }

    public void testGetAllSearchStatements() {
        int expectedListSize = 5;
        int actualListSize = searchStatementDao.getAllSearchStatements().size();
        Assert.assertEquals(expectedListSize, actualListSize);
    }

    public void testGetSearchStatementByPassportId() {
        int expectedListSize = 1;
        int actualListSize = searchStatementDao.getSearchStatementByPassportId(1).size();
        Assert.assertEquals(expectedListSize, actualListSize);
    }

    public void testGetSearchStatementByNotExistPassportId() {
        int expectedListSize = 0;
        int actualListSize = searchStatementDao.getSearchStatementByPassportId(112).size();
        Assert.assertEquals(expectedListSize, actualListSize);
    }

    public void testCreate() throws ParseException, DaoException {
        SearchPackageStatement expectedSearchStatement = new SearchPackageStatement();
        Passport passport = new Passport(); passport.setPassportId(1);
        by.bsuir.spp.bean.document.Package pack = new Package(); pack.setIdPackage(1);
        expectedSearchStatement.setPassport(passport);
        expectedSearchStatement.setPostPackage(pack);
        expectedSearchStatement.setAddress("address1");
        expectedSearchStatement.setPetitionContent("contes");
        expectedSearchStatement.setPhoneNumber("213213");
        expectedSearchStatement.setCurrentDate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2016-02-02").getTime()));
        expectedSearchStatement.setPostManagerName("manager");

        int id = searchStatementDao.create(expectedSearchStatement);
        expectedSearchStatement.setId(id);

        SearchPackageStatement actualSearchStatement = searchStatementDao.read(id);

        Assert.assertEquals(actualSearchStatement, expectedSearchStatement);
    }

    public void testRead() throws ParseException, DaoException {
        SearchPackageStatement expectedSearchStatement = new SearchPackageStatement();
        Passport passport = new Passport(); passport.setPassportId(1);
        by.bsuir.spp.bean.document.Package pack = new Package(); pack.setIdPackage(1);
        expectedSearchStatement.setPassport(passport);
        expectedSearchStatement.setPostPackage(pack);
        expectedSearchStatement.setAddress("address1");
        expectedSearchStatement.setPetitionContent("content1");
        expectedSearchStatement.setPhoneNumber("375292847037");
        expectedSearchStatement.setCurrentDate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2016-02-02").getTime()));
        expectedSearchStatement.setPostManagerName("manager1");
        expectedSearchStatement.setId(1);

        SearchPackageStatement actualSearchStatement = searchStatementDao.read(1);

        Assert.assertEquals(actualSearchStatement, expectedSearchStatement);
    }

    public void testReadNotExist() throws DaoException {
        SearchPackageStatement searchPackageStatement = searchStatementDao.read(123);
        Assert.assertNull(searchPackageStatement);
    }

    public void testUpdate() throws DaoException {
        SearchPackageStatement searchStatement = searchStatementDao.read(1);
        searchStatement.setAddress("newAddress");
        searchStatementDao.update(searchStatement);
        SearchPackageStatement actualSearchStatement = searchStatementDao.read(1);
        Assert.assertEquals(actualSearchStatement, searchStatement);
    }

    public void testDelete() throws DaoException {
        SearchPackageStatement searchPackageStatement = new SearchPackageStatement();
        searchPackageStatement.setId(1);
        searchStatementDao.delete(searchPackageStatement);
    }

    public void testDeleteNotExist() {
        int expectedListCount = searchStatementDao.getAllSearchStatements().size();
        SearchPackageStatement searchPackageStatement = new SearchPackageStatement();
        searchPackageStatement.setId(21);
        int actualListCount = searchStatementDao.getAllSearchStatements().size();
        Assert.assertEquals(expectedListCount, actualListCount);
    }

}
