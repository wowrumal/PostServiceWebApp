package by.bsuir.spp.controller.command.impl.searchstatement;

import by.bsuir.spp.bean.Passport;
import by.bsuir.spp.bean.document.SearchPackageStatement;
import by.bsuir.spp.controller.command.Command;
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

        if (validateParams(request)) {

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
            packageStatement.setPhoneNumber(request.getParameter(RequestParameterName.PHONE_NUMBER));

            Passport passport = new Passport();
            passport.setPassportId(Integer.parseInt(request.getParameter(RequestParameterName.PASSPORT_ID)));
            packageStatement.setPassport(passport);

            by.bsuir.spp.bean.document.Package packagee = new by.bsuir.spp.bean.document.Package();
            packagee.setIdPackage(Integer.parseInt(request.getParameter(RequestParameterName.PACKAGE_ID)));
            packageStatement.setPostPackage(packagee);

            packageStatement.setId(Integer.parseInt(request.getParameter(RequestParameterName.SEARCH_STATEMENT_ID)));

            try {
                searchStatementDao.update(packageStatement);
            } catch (DaoException e) {
                e.printStackTrace();
            }

            return new LoadSearchStatementsCommand().execute(request);
        }
        else {
            return new SelectSearchStatementCommand().execute(request);
        }
    }

    private boolean validateParams(HttpServletRequest request){

        if (getRequestParam(request, RequestParameterName.SEARCH_STATEMENT_ADDRESS) == null ||
                getRequestParam(request, RequestParameterName.PETITION_CONTENT) == null ||
                getRequestParam(request, RequestParameterName.PHONE_NUMBER) == null ||
                getRequestParam(request, RequestParameterName.POST_MANAGER_NAME) == null ||
                getRequestParam(request, RequestParameterName.DATE) == null)
        {
            return false;
        }

        if (getRequestParam(request, RequestParameterName.SEARCH_STATEMENT_ADDRESS).length() > 45  ||
                getRequestParam(request, RequestParameterName.PETITION_CONTENT).length() > 225 ||
                getRequestParam(request, RequestParameterName.PHONE_NUMBER).length() > 45 ||
                getRequestParam(request, RequestParameterName.POST_MANAGER_NAME).length() > 45)
        {
            return false;
        }

        try {
            new SimpleDateFormat("yyyy-MM-dd").parse(getRequestParam(request, RequestParameterName.DATE));
        } catch (ParseException e) {
            return false;
        }

        return true;
    }

    private String getRequestParam(HttpServletRequest request, String parameterName){
        return request.getParameter(parameterName);
    }
}
