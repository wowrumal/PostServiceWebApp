package by.bsuir.spp.dao;

import by.bsuir.spp.bean.Comment;

import java.util.List;

public interface PackageCommentDao extends GenericDao<Comment, Integer> {
    List<Comment> getCommentsForPackage(int packageId);
}
