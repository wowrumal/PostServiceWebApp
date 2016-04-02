package by.bsuir.spp.controller.command.impl.searchstatement;

import by.bsuir.spp.bean.Passport;
import by.bsuir.spp.bean.document.Package;
import by.bsuir.spp.bean.document.SearchPackageStatement;
import by.bsuir.spp.controller.command.Command;
import by.bsuir.spp.controller.constant.RequestParameterName;
import by.bsuir.spp.dao.SearchStatementDao;
import by.bsuir.spp.dao.impl.MySqlSearchStatementDao;
import by.bsuir.spp.exception.controller.command.CommandException;
import by.bsuir.spp.exception.dao.DaoException;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class AddSearchStatementCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        if (validateParams(request)) {

            SearchStatementDao searchStatementDao = MySqlSearchStatementDao.getInstance();

            SearchPackageStatement packageStatement = new SearchPackageStatement();
            packageStatement.setPostManagerName(request.getParameter(RequestParameterName.POST_MANAGER_NAME));

            packageStatement.setCurrentDate(new Date());

            packageStatement.setPetitionContent(request.getParameter(RequestParameterName.PETITION_CONTENT));
            packageStatement.setAddress(request.getParameter(RequestParameterName.SEARCH_STATEMENT_ADDRESS));
            packageStatement.setPhoneNumber(request.getParameter(RequestParameterName.PHONE_NUMBER));

            Passport passport = new Passport();
            passport.setPassportId(Integer.parseInt(request.getParameter(RequestParameterName.PASSPORT_ID)));
            packageStatement.setPassport(passport);

            by.bsuir.spp.bean.document.Package packagee = new Package();
            packagee.setIdPackage(Integer.parseInt(request.getParameter(RequestParameterName.PACKAGE_ID)));
            packageStatement.setPostPackage(packagee);

            try {
                searchStatementDao.create(packageStatement);
            } catch (DaoException e) {
                e.printStackTrace();
            }

            return new GetUserSearchStatementsCommand().execute(request);
        }
        else{
            return new PrepareDataForSearchStatementCreation().execute(request);
        }

    }

    private boolean validateParams(HttpServletRequest request){

        if (getRequestParam(request, RequestParameterName.SEARCH_STATEMENT_ADDRESS) == null ||
                getRequestParam(request, RequestParameterName.PETITION_CONTENT) == null ||
                getRequestParam(request, RequestParameterName.PHONE_NUMBER) == null ||
                getRequestParam(request, RequestParameterName.POST_MANAGER_NAME) == null ||
                getRequestParam(request, RequestParameterName.PACKAGE_ID) == null ||
                getRequestParam(request, RequestParameterName.PASSPORT_ID) == null)
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

        return true;
    }

    private String getRequestParam(HttpServletRequest request, String parameterName){
        return request.getParameter(parameterName);
    }
}
