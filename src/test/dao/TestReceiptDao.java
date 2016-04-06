package dao;

import by.bsuir.spp.bean.document.Receipt;
import by.bsuir.spp.dao.ReceiptDao;
import by.bsuir.spp.dao.connectionpool.IConnectionPool;
import by.bsuir.spp.dao.connectionpool.impl.ConnectionPoolImpl;
import by.bsuir.spp.dao.impl.MySqlReceiptDao;
import by.bsuir.spp.exception.dao.DaoException;
import by.bsuir.spp.exception.dao.connectionpool.ConnectionPoolException;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.*;

import java.io.FileInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestReceiptDao extends DBTestCase {

    private IDataSet[] dataSets;
    private static ReceiptDao receiptDao = MySqlReceiptDao.getInstance();

    private static final IConnectionPool connectionPool = ConnectionPoolImpl.getInstance();


    public TestReceiptDao(String name) throws ConnectionPoolException {
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
                new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/dao/receipt_dataset.xml"))
        };

        return new CompositeDataSet(dataSets);
    }

    @Test
    public void testGetAllReceipts() throws DaoException {
        int expectedSize = receiptDao.getAllReceipts().size();
        receiptDao.delete(new Receipt(){{setReceiptId(1);}});
        expectedSize--;
        int actualSize = receiptDao.getAllReceipts().size();

        Assert.assertEquals(expectedSize, actualSize);
    }

    @Test
    public void testGetUserReceipts() {
        int dummyUserId = 1;
        int expectedListSize = 2;

        int actualListSize = receiptDao.getUserReceipts(dummyUserId).size();

        Assert.assertEquals(actualListSize, expectedListSize);
    }

    @Test
    public void testCreate() throws ParseException, DaoException {
        Receipt receipt = new Receipt();
        receipt.setServiceName("service");
        receipt.setDate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2016-10-12").getTime()));
        receipt.setPaymentData("data");
        receipt.setCost(400);
        receipt.setClientName("client");
        receipt.setUserId(1);

        int receiptId = receiptDao.create(receipt);

        receipt.setReceiptId(receiptId);

        Receipt actualReceipt = receiptDao.read(receiptId);

        Assert.assertEquals(actualReceipt, receipt);
    }

    @Test
    public void testRead() throws ParseException, DaoException {
        Receipt expectedReceipt = new Receipt();
        expectedReceipt.setReceiptId(1);
        expectedReceipt.setUserId(1);
        expectedReceipt.setClientName("client1");
        expectedReceipt.setPaymentData("data1");
        expectedReceipt.setCost(100);
        expectedReceipt.setDate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2016-02-02").getTime()));
        expectedReceipt.setServiceName("service1");


        Receipt actualReceipt = receiptDao.read(1);
        Assert.assertEquals(actualReceipt, expectedReceipt);
    }

    @Test
    public void testReadNotExist() throws DaoException {
        Receipt receipt = receiptDao.read(21);
        Assert.assertNull(receipt);
    }



}
