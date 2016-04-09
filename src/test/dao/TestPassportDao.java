package dao;

import by.bsuir.spp.bean.Passport;
import by.bsuir.spp.dao.PassportDao;
import by.bsuir.spp.dao.connectionpool.IConnectionPool;
import by.bsuir.spp.dao.connectionpool.impl.ConnectionPoolImpl;
import by.bsuir.spp.dao.impl.MySqlPassportDao;
import by.bsuir.spp.exception.dao.DaoException;
import by.bsuir.spp.exception.dao.connectionpool.ConnectionPoolException;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileInputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestPassportDao extends DBTestCase {

    private static final PassportDao passportDao = MySqlPassportDao.getInstance();
    protected static final IConnectionPool connectionPool = ConnectionPoolImpl.getInstance();
    private IDataSet dataSet;

    public TestPassportDao(String name) throws ConnectionPoolException {
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
        return DatabaseOperation.DELETE_ALL;
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        dataSet = new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/dao/passport_dataset.xml"));
        return dataSet;
    }

    @Test
    public void testRead() throws DaoException, ParseException {
        Passport actualPassport = passportDao.read(1);

        Passport expectedPassport = new Passport();
        expectedPassport.setPassportId(1);
        expectedPassport.setPassportNumber("number1");
        expectedPassport.setAddress("address1");
        expectedPassport.setIssuingInstitution("insistution1");
        expectedPassport.setIssueDate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2016-02-02").getTime()));

        Assert.assertEquals(actualPassport, expectedPassport);
    }

    public void testReadUnexpected() throws DaoException, ParseException {
        Passport actualPassport = passportDao.read(1);

        Passport expectedPassport = new Passport();
        expectedPassport.setPassportId(1);
        expectedPassport.setPassportNumber("number22");
        expectedPassport.setAddress("address1");
        expectedPassport.setIssuingInstitution("insistution1");
        expectedPassport.setIssueDate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2016-02-02").getTime()));

        Assert.assertNotEquals(actualPassport, expectedPassport);
    }

    @Test
    public void testReadPes() throws DaoException {
        Passport passport = passportDao.read(1000);
        Assert.assertNull(passport);
    }

    @Test
    public void testCreate() throws ParseException, DaoException {
        Passport expectedPassport = new Passport();
        expectedPassport.setPassportNumber("number12");
        expectedPassport.setAddress("address23");
        expectedPassport.setIssuingInstitution("insistution23");
        expectedPassport.setIssueDate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2016-10-12").getTime()));

        int passportId = passportDao.create(expectedPassport);

        expectedPassport.setPassportId(passportId);

        Passport actualPassport = passportDao.read(passportId);
        Assert.assertEquals(actualPassport, expectedPassport);
    }

    public void testCreateWithWrongParams() throws ParseException, DaoException {
        Passport expectedPassport = new Passport();
        expectedPassport.setPassportNumber(null);
        expectedPassport.setAddress("address23");
        expectedPassport.setIssuingInstitution("insistution23");
        expectedPassport.setIssueDate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2016-10-12").getTime()));

        int passportId = passportDao.create(expectedPassport);

        expectedPassport.setPassportId(passportId);

        Passport actualPassport = passportDao.read(passportId);
        Assert.assertNull(actualPassport);
    }

    @Test(expected = SQLException.class)
    public void testCreateExistPassport() throws ParseException, DaoException {
        Passport dummyPassport = new Passport();
        dummyPassport.setPassportNumber("number1");
        dummyPassport.setAddress("address11");
        dummyPassport.setIssuingInstitution("insistution12");
        dummyPassport.setIssueDate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2016-12-02").getTime()));

        int passportId = passportDao.create(dummyPassport);
    }

    @Test
    public void testUpdatePassport() throws ParseException, DaoException {
        Passport dummyPassport = new Passport();
        dummyPassport.setPassportId(1);
        dummyPassport.setPassportNumber("number22");
        dummyPassport.setAddress("address11");
        dummyPassport.setIssuingInstitution("insistution12");
        dummyPassport.setIssueDate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2016-12-02").getTime()));
        passportDao.update(dummyPassport);

        Passport actualPassport = passportDao.read(1);

        Assert.assertEquals(actualPassport, dummyPassport);
    }

    @Test(expected = SQLException.class)
    public void testUpdateNotExistedPassport() throws ParseException, DaoException {
        Passport dummyPassport = new Passport();
        dummyPassport.setPassportId(23);
        dummyPassport.setPassportNumber("number22");
        dummyPassport.setAddress("address11");
        dummyPassport.setIssuingInstitution("insistution12");
        dummyPassport.setIssueDate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2016-12-02").getTime()));
        passportDao.update(dummyPassport);
    }


    @Test
    public void testDeletePassport() throws DaoException {
        int dummyPassportId = 2;
        passportDao.delete(new Passport() {{
            setPassportId(dummyPassportId);
        }});
        Passport actualPassport = passportDao.read(dummyPassportId);

        Assert.assertNull(actualPassport);
    }

    public void testDeleteNotExist() throws DaoException {
        int expectedListSize = passportDao.getAllPassports().size();
        passportDao.delete(new Passport(){{setPassportId(213);}});
        int actualListSize = passportDao.getAllPassports().size();
        Assert.assertEquals(expectedListSize, actualListSize);
    }

    @Test
    public void testGetAllPassports() throws DaoException {
        int expectedCount = passportDao.getAllPassports().size();
        passportDao.delete(new Passport(){{setPassportId(2);}});
        expectedCount--;
        int actualCount = passportDao.getAllPassports().size();
        Assert.assertEquals(expectedCount, actualCount);
    }

    public void testGetUnexpectedAllPassports() {
        int expectedCount = 123;
        int actualCount = passportDao.getAllPassports().size();
        Assert.assertNotEquals(expectedCount, actualCount);
    }

    @Test
    public void testGetIdPassports() {
        List<Integer> expectedList = new ArrayList<Integer>(){{
            add(1);
            add(2);
            add(3);
            add(4);
            add(5);
            add(6);
            add(7);
        }};

        List<Integer> actualList = passportDao.getIdPassports();
        Assert.assertEquals(actualList, expectedList);
    }

    public void testGetUnexpectedIdPassports() {
        List<Integer> expectedList = new ArrayList<Integer>(){{
            add(1);
            add(2);
            add(3);
            add(23);
            add(5);
            add(6);
            add(7);
        }};

        List<Integer> actualList = passportDao.getIdPassports();
        Assert.assertNotEquals(actualList, expectedList);
    }
}
