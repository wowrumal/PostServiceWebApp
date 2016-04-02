package by.bsuir.spp.controller.command.impl.searchstatement;

import by.bsuir.spp.bean.document.SearchPackageStatement;
import by.bsuir.spp.controller.command.Command;
import by.bsuir.spp.controller.constant.JspPageName;
import by.bsuir.spp.controller.constant.RequestParameterName;
import by.bsuir.spp.dao.PackageDao;
import by.bsuir.spp.dao.SearchStatementDao;
import by.bsuir.spp.dao.impl.MySqlPackageDao;
import by.bsuir.spp.dao.impl.MySqlSearchStatementDao;
import by.bsuir.spp.exception.controller.command.CommandException;
import by.bsuir.spp.exception.dao.DaoException;

import javax.servlet.http.HttpServletRequest;

public class SelectSearchStatementCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        int statementId = Integer.parseInt(request.getParameter(RequestParameterName.SEARCH_STATEMENT_ID));

        SearchStatementDao searchStatementDao = MySqlSearchStatementDao.getInstance();
        PackageDao packageDao = MySqlPackageDao.getInstance();
        try {
            SearchPackageStatement searchPackageStatement = searchStatementDao.read(statementId);
            searchPackageStatement.setPostPackage(packageDao.read(searchPackageStatement.getPostPackage().getIdPackage()));
            request.setAttribute(RequestParameterName.SEARCH_STATEMENT, searchPackageStatement);
        } catch (DaoException e) {
            e.printStackTrace();
        }

        if (request.getParameter(RequestParameterName.SUB_COMMAND) != null) {
            return new PrepareDataForSearchStatementCreation().execute(request);
        }

        return JspPageName.SEARCH_STATEMENT;
    }
}
