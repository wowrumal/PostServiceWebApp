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

        SearchPackageStatement packageStatement = getStatement(request);
        request.getSession().setAttribute(RequestParameterName.SEARCH_STATEMENT, packageStatement);

        if (validateParams(request)) {

            SearchStatementDao searchStatementDao = MySqlSearchStatementDao.getInstance();

            try {
                searchStatementDao.create(packageStatement);
            } catch (DaoException e) {
                e.printStackTrace();
            }
            request.getSession().removeAttribute(RequestParameterName.SEARCH_STATEMENT);
            return "controller?command=get_user_search_statements";
        }
        else{
            return "controller?command=prepare_data_for_creation_search_statement";
        }

    }

    private SearchPackageStatement getStatement(HttpServletRequest request) {
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

        return packageStatement;
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
