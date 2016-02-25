package by.bsuir.spp.controller.command.impl.searchstatement;

import by.bsuir.spp.bean.Passport;
import by.bsuir.spp.bean.document.*;
import by.bsuir.spp.controller.command.Command;
import by.bsuir.spp.controller.constant.JspPageName;
import by.bsuir.spp.controller.constant.RequestParameterName;
import by.bsuir.spp.dao.SearchStatementDao;
import by.bsuir.spp.dao.impl.MySqlSearchStatementDao;
import by.bsuir.spp.exception.controller.command.CommandException;
import by.bsuir.spp.exception.dao.DaoException;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UpdateSearchStatementCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        SearchStatementDao searchStatementDao = MySqlSearchStatementDao.getInstance();

        SearchPackageStatement packageStatement = new SearchPackageStatement();
        packageStatement.setPostManagerName(request.getParameter(RequestParameterName.POST_MANAGER_NAME));
        try {
            packageStatement.setCurrentDate(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter(RequestParameterName.DATE)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        packageStatement.setPetitionContent(request.getParameter(RequestParameterName.PETITION_CONTENT));
        packageStatement.setAddress(request.getParameter(RequestParameterName.SEARCH_STATEMENT_ADDRESS));
        packageStatement.setPhoneNumber(Integer.parseInt(request.getParameter(RequestParameterName.PHONE_NUMBER)));

        Passport passport = new Passport();
        passport.setPassportId(Integer.parseInt(request.getParameter(RequestParameterName.PASSPORT_ID)));
        packageStatement.setPassport(passport);

        by.bsuir.spp.bean.document.Package packagee = new by.bsuir.spp.bean.document.Package();
        packagee.setIdPackage(Integer.parseInt(request.getParameter(RequestParameterName.PACKAGE_ID)));
        packageStatement.setPostPackage(packagee);

        packageStatement.setId(Integer.parseInt(request.getParameter(RequestParameterName.PASSPORT_ID)));

        try {
            searchStatementDao.update(packageStatement);
        } catch (DaoException e) {
            e.printStackTrace();
        }

        return JspPageName.SEARCH_STATEMENTS;
    }
}
