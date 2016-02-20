package by.bsuir.spp.dao;

import by.bsuir.spp.bean.document.SearchPackageStatement;

import java.util.List;

/**
 * Created by Кирилл on 2/20/2016.
 */
public interface SearchStatementDao extends GenericDao<SearchPackageStatement, Integer> {

    List<SearchPackageStatement> getAllSearchStatements();
}
