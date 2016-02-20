package by.bsuir.spp.dao.impl;

import by.bsuir.spp.bean.document.SearchPackageStatement;
import by.bsuir.spp.dao.SearchStatementDao;
import by.bsuir.spp.exception.dao.DaoException;

import java.util.List;

/**
 * Created by Кирилл on 2/20/2016.
 */
public class MySqlSearchStatementDao implements SearchStatementDao {

    private static final MySqlSearchStatementDao instance = new MySqlSearchStatementDao();

    private MySqlSearchStatementDao() {}

    public static MySqlSearchStatementDao getInstance() {
        return instance;
    }

    private static final String INSERT_SEARCH_STATEMENT_QUERY = "INSERT into `search_statement` (address, phoneNumber, passportID, petitionContent, packageID, currentDate, managerName) "+
            "values (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_SEARCH_STATEMENT_BY_ID = "select * from `search_statement` where id=?";
    private static final String DELETE_SEARCH_STATEMENT_BY_ID = "delete from `search_statement` where id=?";
    private static final String UPDATE_SEARCH_STATEMENT_BY_ID = "update `search_statement` set address=?, phoneNumber=?, passportID=?, petitionContent=?, packageID=?, currentDate=?, managerName=? "+
            "where id=?";
    private static final String SELECT_ALL_SEARCH_STATEMENT = "select * from `search_statement`";

    @Override
    public List<SearchPackageStatement> getAllSearchStatements() {
        return null;
    }

    @Override
    public Integer create(SearchPackageStatement newInstance) throws DaoException {
        return null;
    }

    @Override
    public SearchPackageStatement read(Integer id) throws DaoException {
        return null;
    }

    @Override
    public void update(SearchPackageStatement obj) throws DaoException {

    }

    @Override
    public void delete(SearchPackageStatement obj) throws DaoException {

    }
}
