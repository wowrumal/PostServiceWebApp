package dao;

import by.bsuir.spp.bean.document.PrepaymentBookStatement;
import by.bsuir.spp.dao.PrepaymentBookDao;
import by.bsuir.spp.dao.connectionpool.IConnectionPool;
import by.bsuir.spp.dao.connectionpool.impl.ConnectionPoolImpl;
import by.bsuir.spp.dao.impl.MySqlPrepaymentBookDao;
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

public class TestPrepaymentBookDao extends DBTestCase {
    private static PrepaymentBookDao prepaymentBookDao = MySqlPrepaymentBookDao.getInstance();

    private static final IConnectionPool connectionPool = ConnectionPoolImpl.getInstance();
    private IDataSet[] dataSets;

    public TestPrepaymentBookDao(String name) throws ConnectionPoolException {
        super(name);
        System.setProperty(
                PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS,
                "com.mysql.jdbc.Driver");
        System.setProperty(
                PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL,
                "jdbc:mysql://localhost:3307/test_post_service_db?useUnicode=true&characterEncoding=UTF8&autoReconnect=true");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "root");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "123456");
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
                new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/dao/prepayment_book_dataset.xml"))
        };

        return new CompositeDataSet(dataSets);
    }

    public void testGetAllPrepaymentBooks() {
        int expectedListSize = 5;
        int actualListSize = prepaymentBookDao.getAllPrepaymentBooks().size();
        Assert.assertEquals(expectedListSize, actualListSize);
    }

    public void testGetPrepaymentBooksByPassportId() {
        int expectedListSize = 1;
        int actualListSize = prepaymentBookDao.getPrepaymentBooksByPassportId(1).size();
        Assert.assertEquals(expectedListSize, actualListSize);
    }

    public void testGetPrepaymentBooksByNotExistedPassport() {
        int expectedListSize = 0;
        int actualListSize = prepaymentBookDao.getPrepaymentBooksByPassportId(41).size();
        Assert.assertEquals(expectedListSize, actualListSize);
    }

    public void testCreate() throws ParseException, DaoException {
        PrepaymentBookStatement statement = new PrepaymentBookStatement();
        statement.setDate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2016-02-02").getTime()));
        statement.setPassportId(2);
        statement.setClientNumber(213);
        statement.setBookkeeperName("name");
        statement.setClientName("client");
        statement.setOrganizationHeadName("head");
        statement.setUnpaidCost(200);

        int id = prepaymentBookDao.create(statement);
        statement.setStatementNumber(id);

        PrepaymentBookStatement actualStatement = prepaymentBookDao.read(id);

        Assert.assertEquals(actualStatement, statement);
    }

    public void testRead() throws ParseException, DaoException {
        PrepaymentBookStatement statement = new PrepaymentBookStatement();
        statement.setDate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2016-02-02").getTime()));
        statement.setPassportId(1);
        statement.setClientNumber(1);
        statement.setBookkeeperName("keeper1");
        statement.setClientName("client1");
        statement.setOrganizationHeadName("head1");
        statement.setUnpaidCost(100);
        statement.setStatementNumber(1);

        PrepaymentBookStatement actualStatement = prepaymentBookDao.read(1);
        Assert.assertEquals(actualStatement, statement);
    }

    public void testReadNotExist() throws DaoException {
        PrepaymentBookStatement statement = prepaymentBookDao.read(213);
        Assert.assertNull(statement);
    }

    public void testUpdate() throws DaoException {
        PrepaymentBookStatement dummyStatement = prepaymentBookDao.read(1);
        dummyStatement.setUnpaidCost(123);
        prepaymentBookDao.update(dummyStatement);
        PrepaymentBookStatement actualStatement = prepaymentBookDao.read(1);
        Assert.assertEquals(actualStatement, dummyStatement);
    }

    public void testDelete() throws DaoException {
        PrepaymentBookStatement statement = new PrepaymentBookStatement();
        statement.setStatementNumber(1);
        prepaymentBookDao.delete(statement);
        PrepaymentBookStatement actualStatement = prepaymentBookDao.read(1);
        Assert.assertNull(actualStatement);
    }

    public void testDeleteNotExist() throws DaoException {
        int expectedListSize = prepaymentBookDao.getAllPrepaymentBooks().size();
        prepaymentBookDao.delete(new PrepaymentBookStatement(){{setStatementNumber(213);}});
        int actualListSize = prepaymentBookDao.getAllPrepaymentBooks().size();
        Assert.assertEquals(expectedListSize, actualListSize);

    }


}
