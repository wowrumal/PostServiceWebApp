package dao;

import by.bsuir.spp.bean.Passport;
import by.bsuir.spp.bean.document.Advertisement;
import by.bsuir.spp.bean.document.Package;
import by.bsuir.spp.dao.AdvertisementDao;
import by.bsuir.spp.dao.connectionpool.IConnectionPool;
import by.bsuir.spp.dao.connectionpool.impl.ConnectionPoolImpl;
import by.bsuir.spp.dao.impl.MySqlAdvertisementDao;
import by.bsuir.spp.exception.dao.DaoException;
import by.bsuir.spp.exception.dao.connectionpool.ConnectionPoolException;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileInputStream;
import java.sql.SQLException;

public class TestAdvertisementDao extends DBTestCase {

    private IDataSet[] dataSets;
    private static AdvertisementDao advertisementDao = MySqlAdvertisementDao.getInstance();

    private static final IConnectionPool connectionPool = ConnectionPoolImpl.getInstance();


    public TestAdvertisementDao(String name) throws ConnectionPoolException {
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
                new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/dao/new_package_dataset.xml")),
                new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/dao/advertisement_dataset.xml"))
        };

        return new CompositeDataSet(dataSets);
    }

    public void testGetAllAdvertisements() {
        int expectedListSize = 4;
        int actualListSize = advertisementDao.getAllAdvertisement().size();
        Assert.assertEquals(expectedListSize, actualListSize);
    }

    public void testGetAdvertisementsByPassportId() {
        int dummyPassportId = 1;
        int expectedSize = 2;
        int actualSize = advertisementDao.getAdvertisementsByPassportId(dummyPassportId).size();
        Assert.assertEquals(expectedSize, actualSize);
    }

    public void testCreate() throws DaoException {
        Advertisement dummyAdvertisement = new Advertisement();
        Passport passport = new Passport(); passport.setPassportId(1);
        by.bsuir.spp.bean.document.Package pack = new Package(); pack.setIdPackage(5);
        dummyAdvertisement.setPassport(passport);
        dummyAdvertisement.setPostPackage(pack);
        dummyAdvertisement.setWeight(200);
        dummyAdvertisement.setAddressForGetting("address");

        int advertId = advertisementDao.create(dummyAdvertisement);

        Advertisement actualAdvertisement = advertisementDao.read(advertId);

        Assert.assertEquals(actualAdvertisement, dummyAdvertisement);
    }

    @Test(expected = SQLException.class)
    public void testCreateExist() throws DaoException {
        Advertisement dummyAdvertisement = new Advertisement();
        Passport passport = new Passport(); passport.setPassportId(1);
        by.bsuir.spp.bean.document.Package pack = new Package(); pack.setIdPackage(1);
        dummyAdvertisement.setPassport(passport);
        dummyAdvertisement.setPostPackage(pack);
        dummyAdvertisement.setWeight(200);
        dummyAdvertisement.setAddressForGetting("address");

        int advertId = advertisementDao.create(dummyAdvertisement);
    }

    public void testRead() throws DaoException {
        Advertisement advertisement = new Advertisement();
        Package packag = new Package();
        packag.setIdPackage(1);
        Passport passport = new Passport();
        passport.setPassportId(1);
        advertisement.setPassport(passport);
        advertisement.setPostPackage(packag);
        advertisement.setWeight(20);
        advertisement.setCost(30);
        advertisement.setAddressForGetting("address1");

        Advertisement actualAdvertisement = advertisementDao.read(1);

        Assert.assertEquals(advertisement, actualAdvertisement);
    }

    public void testReadNotExist() throws DaoException {
        Advertisement advertisement = advertisementDao.read(21);
        Assert.assertNull(advertisement);
    }


    public void testUpdate() throws DaoException {
        Advertisement advertisement = advertisementDao.read(1);
        advertisement.setAddressForGetting("newaddress");
        advertisementDao.update(advertisement);
        Advertisement actualAdvertisement = advertisementDao.read(1);

        Assert.assertEquals(advertisement, actualAdvertisement);
    }

    public void testDelete() throws DaoException {
        int dummyAdvertId = 2;
        Advertisement advertisement = new Advertisement();
        Package pack = new Package();
        pack.setIdPackage(dummyAdvertId);
        advertisement.setPostPackage(pack);
        advertisementDao.delete(advertisement);

        Advertisement actualAdver = advertisementDao.read(dummyAdvertId);
        Assert.assertNull(actualAdver);
    }

    public void testDeleteNotExist() throws DaoException {
        int expectedListSize = advertisementDao.getAllAdvertisement().size();
        advertisementDao.delete(new Advertisement(){{setPostPackage(new Package(){{setIdPackage(134);}});}});
        int actualListSize = advertisementDao.getAllAdvertisement().size();
        Assert.assertEquals(expectedListSize, actualListSize);

    }

}
