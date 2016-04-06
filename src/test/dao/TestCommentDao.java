package dao;

import by.bsuir.spp.bean.Comment;
import by.bsuir.spp.dao.PackageCommentDao;
import by.bsuir.spp.dao.connectionpool.IConnectionPool;
import by.bsuir.spp.dao.connectionpool.impl.ConnectionPoolImpl;
import by.bsuir.spp.dao.impl.MySqlPackageCommentDao;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestCommentDao extends DBTestCase {
    private IDataSet[] dataSets;
    private static PackageCommentDao commentDao = MySqlPackageCommentDao.getInstance();

    private static final IConnectionPool connectionPool = ConnectionPoolImpl.getInstance();


    public TestCommentDao(String name) throws ConnectionPoolException {
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
        dataSets = new IDataSet[] {
                new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/dao/passport_dataset.xml")),
                new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/dao/user_dataset.xml")),
                new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/dao/package_dataset.xml")),
                new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/dao/new_package_dataset.xml")),
                new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/dao/package_comment_dataset.xml")),
        };

        return new CompositeDataSet(dataSets);
    }

    @Test
    public void testCreate() throws ParseException, DaoException {
        Comment dummyComment = new Comment();
        dummyComment.setDate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2016-02-02").getTime()));
        dummyComment.setText("asda");
        dummyComment.setPackageId(3);

        int id = commentDao.create(dummyComment);
        dummyComment.setId(id);

        Comment actualComment = commentDao.getCommentsForPackage(3).get(0);

        Assert.assertEquals(actualComment, dummyComment);
    }

    @Test
    public void testCreateWithWrongParams() throws DaoException, ParseException {
        Comment dummyComment = new Comment();
        dummyComment.setDate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2016-02-02").getTime()));
        dummyComment.setText("asda");

        try {
            int id = commentDao.create(dummyComment);
        } catch (Exception e) {
            Assert.assertEquals(e.getClass(), DaoException.class);
        }


    }

    @Test(expected = UnsupportedOperationException.class)
    public void testRead() throws DaoException {
        try {
            Comment comment = commentDao.read(4);
        } catch (Exception e) {
            Assert.assertEquals(e.getClass(), UnsupportedOperationException.class);
        }
    }

    public void testUpdate() {
        try {
            commentDao.update(new Comment());
        } catch (Exception e) {
            Assert.assertEquals(e.getClass(), UnsupportedOperationException.class);
        }
    }

    public void testDelete() {
        try {
            commentDao.delete(new Comment());
        } catch (Exception e) {
            Assert.assertEquals(e.getClass(), UnsupportedOperationException.class);
        }
    }

    public void testGetCommentsForPackage() {
        int expectedListSize = 2;
        int actualListSize = commentDao.getCommentsForPackage(1).size();
        Assert.assertEquals(expectedListSize, actualListSize);
    }


}
