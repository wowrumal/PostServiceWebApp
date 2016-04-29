package by.bsuir.spp.controller.command.impl.searchstatement;

import by.bsuir.spp.bean.User;
import by.bsuir.spp.bean.document.SearchPackageStatement;
import by.bsuir.spp.controller.command.Command;
import by.bsuir.spp.controller.constant.JspPageName;
import by.bsuir.spp.controller.constant.RequestParameterName;
import by.bsuir.spp.dao.PackageDao;
import by.bsuir.spp.dao.SearchStatementDao;
import by.bsuir.spp.dao.impl.MySqlPackageDao;
import by.bsuir.spp.dao.impl.MySqlSearchStatementDao;
import by.bsuir.spp.exception.controller.command.CommandException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetUserSearchStatementsCommand implements Command {


    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = JspPageName.LOGIN_PAGE;

        try {
            int passportId = ((User)request.getSession().getAttribute(RequestParameterName.USER)).getPassport().getPassportId();
            SearchStatementDao searchStatementDao = MySqlSearchStatementDao.getInstance();
            List<SearchPackageStatement> statementList = searchStatementDao.getSearchStatementByPassportId(passportId);
            PackageDao packageDao = MySqlPackageDao.getInstance();
            for (SearchPackageStatement packageStatement : statementList) {
                packageStatement.setPostPackage(packageDao.read(packageStatement.getPostPackage().getIdPackage()));
            }

            request.setAttribute(RequestParameterName.SEARCH_STATEMENTS, statementList);
            page = JspPageName.USER_SEARCHSTATEMENTS;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return page;
    }
}
