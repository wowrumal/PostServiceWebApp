package by.bsuir.spp.dao.impl;

import by.bsuir.spp.bean.Comment;
import by.bsuir.spp.dao.PackageCommentDao;
import by.bsuir.spp.dao.connectionpool.impl.ConnectionPoolImpl;
import by.bsuir.spp.exception.dao.DaoException;
import by.bsuir.spp.exception.dao.connectionpool.ConnectionPoolException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlPackageCommentDao implements PackageCommentDao {

    private static final PackageCommentDao instance = new MySqlPackageCommentDao();

    private MySqlPackageCommentDao(){}

    public static PackageCommentDao getInstance() {
        return instance;
    }


    private static final String INSERT_COMMENT_SQL = "insert INTO package_comment (`text`, `date`, packageId) values(?,?,?)";
    private static final String SELECT_COMMENTS_BY_PACKAGE = "select * from package_comment WHERE packageId = ?";

    @Override
    public Integer create(Comment newInstance) throws DaoException {
        try (Connection connection = ConnectionPoolImpl.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_COMMENT_SQL)) {
            statement.setString(1, newInstance.getText());
            statement.setDate(2, new Date(newInstance.getDate().getTime()));
            statement.setInt(3, newInstance.getPackageId());

            statement.execute();
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
        }
        return 1;
    }

    @Override
    public Comment read(Integer id) throws DaoException {
        return null;
    }

    @Override
    public void update(Comment obj) throws DaoException {

    }

    @Override
    public void delete(Comment obj) throws DaoException {

    }

    @Override
    public List<Comment> getCommentsForPackage(int packageId) {
        List<Comment> comments = new ArrayList<>();

        try (Connection connection = ConnectionPoolImpl.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_COMMENTS_BY_PACKAGE)) {
            statement.setInt(1, packageId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Comment comment = new Comment();
                    comment.setId(resultSet.getInt(1));
                    comment.setText(resultSet.getString(2));
                    comment.setDate(resultSet.getDate(3));
                    comment.setPackageId(resultSet.getInt(4));
                    comments.add(comment);
                }
            }
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
        }

        return comments;
    }
}
