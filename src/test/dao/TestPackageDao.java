package dao;

import by.bsuir.spp.bean.User;
import by.bsuir.spp.bean.document.Package;
import by.bsuir.spp.dao.PackageDao;
import by.bsuir.spp.dao.connectionpool.IConnectionPool;
import by.bsuir.spp.dao.connectionpool.impl.ConnectionPoolImpl;
import by.bsuir.spp.dao.impl.MySqlPackageDao;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestPackageDao extends DBTestCase {
    private IDataSet[] dataSets;
    private static PackageDao packageDao= MySqlPackageDao.getInstance();

    private static final IConnectionPool connectionPool = ConnectionPoolImpl.getInstance();


    public TestPackageDao(String name) throws ConnectionPoolException {
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
                new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/dao/new_package_dataset.xml"))
        };

        return new CompositeDataSet(dataSets);
    }

    public void testCreate() throws ParseException, DaoException {
        by.bsuir.spp.bean.document.Package expectedPackage = new Package();
        expectedPackage.setDate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2016-02-02").getTime()));
        expectedPackage.setPassportId(1);
        expectedPackage.setDeleted(false);
        User user = new User(); user.setId(1);
        expectedPackage.setGetterUser(user);
        expectedPackage.setAddress("address112");
        expectedPackage.setPostIndex(213);
        expectedPackage.setType("type");
        expectedPackage.setStatus("доставляется");

        int packaId = packageDao.create(expectedPackage);

        expectedPackage.setIdPackage(packaId);

        Package actualPackage = packageDao.read(packaId);
        expectedPackage.setTrackNumber(actualPackage.getTrackNumber());

        Assert.assertEquals(actualPackage, expectedPackage);
    }

    public void testRead() throws ParseException, DaoException {
        Package myPackage = new Package();
        myPackage.setIdPackage(1);
        myPackage.setType("package");
        myPackage.setDate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2016-02-02").getTime()));
        myPackage.setSenderName("stas");
        User user = new User();user.setId(1);
        myPackage.setGetterUser(user);
        myPackage.setAddress("address1");
        myPackage.setPostIndex(1);
        myPackage.setPassportId(1);
        myPackage.setDeleted(false);
        myPackage.setStatus("process");

        Package actualPackage = packageDao.read(1);

        Assert.assertEquals(actualPackage, myPackage);
    }

    public void testReadNotExist() throws DaoException {
        Package pack = packageDao.read(213);
        Assert.assertNull(pack);
    }

    public void testUpdate() throws ParseException, DaoException {
        Package myPackage = packageDao.read(2);
        myPackage.setAddress("asdasd");
        packageDao.update(myPackage);
        Package actualPackage = packageDao.read(2);

        Assert.assertEquals(actualPackage, myPackage);
    }

    public void testDelete() throws DaoException {
        packageDao.delete(new Package() {{
            setIdPackage(2);
        }});
        Package pack = packageDao.read(2);
        Assert.assertTrue(pack.isDeleted());
    }

    public void testDeleteNotExist() throws DaoException {
        int expectedListSize = packageDao.getAllPackages().size();
        packageDao.delete(new Package(){{setIdPackage(123);}});
        int actualListSize = packageDao.getAllPackages().size();
        Assert.assertEquals(expectedListSize, actualListSize);
    }

    public void testGetAllPackages() {
        int expectedSize = 5;
        int actualSize = packageDao.getAllPackages().size();
        Assert.assertEquals(expectedSize, actualSize);
    }

    public void testGetPackagesIds() {
        List<Integer> list = new ArrayList<Integer>() {{
            add(1);
            add(2);
            add(3);
            add(4);
            add(5);
        }};

        List<Integer> actualList = packageDao.getPackageIds();

        Assert.assertEquals(actualList, list);
    }

    public void testGetPackagesByPassportId() {
        int expectedCount = 2;
        int actualCount = packageDao.getPackagesByPassportId(1).size();
        Assert.assertEquals(expectedCount, actualCount);
    }

    public void testGetNewPackageIds() {
        int expectedSize = 4;
        int actualSize = packageDao.getNewPackageIds().size();

        Assert.assertEquals(expectedSize, actualSize);
    }

    public void testAddPackageToNewPackages() {
        int expectedSize = 5;
        packageDao.addPackageToNewPackages(5);
        int actualSize = packageDao.getNewPackageIds().size();

        Assert.assertEquals(expectedSize, actualSize);
    }

    public void testDeleteNewPackage() {
        int expectedSize = 3;
        packageDao.deleteNewPackage(1);
        int actualSize = packageDao.getNewPackageIds().size();
        Assert.assertEquals(expectedSize, actualSize);
    }

    public void testDeleteNewPackageNotExist() {
        int expectedListSize = packageDao.getNewPackageIds().size();
        packageDao.deleteNewPackage(23);
        int actualListSize = packageDao.getNewPackageIds().size();
        Assert.assertEquals(expectedListSize, actualListSize);
    }

    public void testUpdateStatus() throws DaoException {
        int packId = 1;
        String dummyStatus = "statusss";
        packageDao.updateStatus(packId, dummyStatus);
        Package pack = packageDao.read(1);
        Assert.assertEquals(dummyStatus, pack.getStatus());
    }


}
