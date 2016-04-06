package dao;

import by.bsuir.spp.bean.Passport;
import by.bsuir.spp.bean.User;
import by.bsuir.spp.bean.UserType;
import by.bsuir.spp.dao.UserDao;
import by.bsuir.spp.dao.connectionpool.IConnectionPool;
import by.bsuir.spp.dao.connectionpool.impl.ConnectionPoolImpl;
import by.bsuir.spp.dao.impl.MySqlUserDao;
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
import java.util.ArrayList;
import java.util.List;

public class TestUserDao extends DBTestCase{

    private IDataSet[] dataSets;

    private static UserDao userDao = MySqlUserDao.getInstance();
    protected static final IConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

    public TestUserDao(String name) throws ConnectionPoolException {
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
                new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/dao/user_dataset.xml"))
        };

        return new CompositeDataSet(dataSets);
    }

    @Test
    public void testCreate() throws DaoException {
        User dummyUser = new User();
        dummyUser.setLogin("login24");
        dummyUser.setPassword("passsword");
        dummyUser.setFirstName("name123");
        dummyUser.setMiddleName("mid12");
        dummyUser.setSecondName("sevd1");

        Passport passport = new Passport();
        passport.setPassportId(1);
        dummyUser.setPassport(passport);
        dummyUser.setUserRole(UserType.CLIENT);
        dummyUser.setEmail("email@asdsad.vy");
        dummyUser.setPhone("21312342");

        int userId = userDao.create(dummyUser);
        dummyUser.setId(userId);

        User actualUser = userDao.read(userId);

        Assert.assertEquals(actualUser, dummyUser);
    }

    @Test(expected = DaoException.class)
    public void testCreateWithExistedLogin() throws DaoException {
        User dummyUser = new User();
        dummyUser.setLogin("login1");
        dummyUser.setPassword("passsword");
        dummyUser.setFirstName("name123");
        dummyUser.setMiddleName("mid12");
        dummyUser.setSecondName("sevd1");

        Passport passport = new Passport();
        passport.setPassportId(1);
        dummyUser.setPassport(passport);
        dummyUser.setUserRole(UserType.CLIENT);
        dummyUser.setEmail("email@asdsad.vy");
        dummyUser.setPhone("21312342");

        int userId = userDao.create(dummyUser);
    }

    @Test
    public void testRead() throws DaoException {
        User dummyUser = new User();
        dummyUser.setLogin("login1");
        dummyUser.setPassword("password1");
        dummyUser.setFirstName("name1");
        dummyUser.setMiddleName("middle1");
        dummyUser.setSecondName("second1");
        dummyUser.setId(1);

        Passport passport = new Passport();
        passport.setPassportId(1);
        dummyUser.setPassport(passport);
        dummyUser.setUserRole(UserType.ADMIN);
        dummyUser.setEmail("orange1.css@yam.by");
        dummyUser.setPhone("375292847037");

        User actualUser = userDao.read(1);
        Assert.assertEquals(actualUser, dummyUser);
    }

    @Test
    public void testReadNotExist() throws DaoException {
        User expectedUser = userDao.read(21);
        Assert.assertNull(expectedUser);
    }

    @Test
    public void testUpdate() throws DaoException {
        User dummyUser = new User();
        dummyUser.setLogin("login24");
        dummyUser.setPassword("password2");
        dummyUser.setFirstName("name123");
        dummyUser.setMiddleName("mid12");
        dummyUser.setSecondName("sevd1");

        Passport passport = new Passport();
        passport.setPassportId(2);
        dummyUser.setPassport(passport);
        dummyUser.setUserRole(UserType.CLIENT);
        dummyUser.setEmail("email@asdsad.vy");
        dummyUser.setPhone("21312342");
        dummyUser.setId(2);

        userDao.update(dummyUser);

        User actualUser = userDao.read(2);

        Assert.assertEquals(actualUser, dummyUser);
    }

    @Test
    public void testDelete() throws DaoException {
        int dummyUserId = 3;

        userDao.delete(new User(){{setId(dummyUserId);}});

        User actualUser = userDao.read(dummyUserId);

        Assert.assertNull(actualUser);
    }

    public void testDeleteNotExist() throws DaoException {
        int expectedListSize = userDao.getAllUsers().size();
        userDao.delete(new User(){{setId(24);}});
        int actualListSize = userDao.getAllUsers().size();
        Assert.assertEquals(expectedListSize, actualListSize);
    }

    @Test
    public void testGetAllUsers() throws DaoException {
        int expectedSize = userDao.getAllUsers().size();
        userDao.delete(new User(){{setId(1);}});
        int actualSize = userDao.getAllUsers().size();
        expectedSize--;
        Assert.assertEquals(expectedSize, actualSize);
    }

    @Test
    public void testGetUserIds() {
        List<Integer> expectedList = new ArrayList<Integer>(){{
            add(1);
            add(2);
            add(3);
            add(4);
            add(5);
        }};

        List<Integer> actualList = userDao.getUserIds();

        Assert.assertEquals(expectedList, actualList);
    }

    @Test
    public void testCheckUser() {
        User dummyUser = new User(){{
            setLogin("login2");
            setPassword("password2");
        }};

        User actualUser = userDao.checkUser(dummyUser);
        Assert.assertNotNull(actualUser);
    }

    @Test
    public void testCheckUserDoesntExist() {
        User dummyUser = new User(){{
            setLogin("login231");
            setPassword("password2123");
        }};

        User actualUser = userDao.checkUser(dummyUser);
        Assert.assertNull(actualUser);
    }

    @Test
    public void testCheckLoginExist() {
        String dummyLogin = "login1";

        boolean result = userDao.checkLogin(dummyLogin);

        Assert.assertFalse(result);
    }

    @Test
    public void testCheckLoginNotExist() {
        String dummyLogin = "login123";
        boolean result = userDao.checkLogin(dummyLogin);
        Assert.assertTrue(result);
    }


}
