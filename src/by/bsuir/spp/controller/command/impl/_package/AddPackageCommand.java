package by.bsuir.spp.controller.command.impl._package;

import by.bsuir.spp.bean.User;
import by.bsuir.spp.controller.command.Command;
import by.bsuir.spp.controller.constant.RequestParameterName;
import by.bsuir.spp.dao.PackageDao;
import by.bsuir.spp.dao.impl.MySqlPackageDao;
import by.bsuir.spp.exception.controller.command.CommandException;
import by.bsuir.spp.exception.dao.DaoException;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by Кирилл on 2/21/2016.
 */
public class AddPackageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        if (validateParams(request)) {

            by.bsuir.spp.bean.document.Package myPackage = new by.bsuir.spp.bean.document.Package();

            myPackage.setType(request.getParameter(RequestParameterName.PACKAGE_TYPE));

            myPackage.setDate(new Date());

            myPackage.setSenderName(request.getParameter(RequestParameterName.PACKAGE_SENDER_NAME));
            myPackage.setGetterUser(new User() {{
                setId(Integer.parseInt(request.getParameter(RequestParameterName.PACKAGE_GETTER_NAME)));
            }});
            myPackage.setAddress(request.getParameter(RequestParameterName.PACKAGE_ADDRESS));
            myPackage.setPostIndex(Integer.parseInt(request.getParameter(RequestParameterName.PACKAGE_POST_INDEX)));
            myPackage.setPassportId(((User) request.getSession().getAttribute(RequestParameterName.USER)).getPassport().getPassportId());

            PackageDao packageDao = MySqlPackageDao.getInstance();
            int packageId = 0;
            try {
                packageId = packageDao.create(myPackage);
                packageDao.addPackageToNewPackages(packageId);
            } catch (DaoException e) {
                e.printStackTrace();
            }

            request.setAttribute(RequestParameterName.PACKAGE_ID, packageId);

            return new GetPackagesForUserCommand().execute(request);
        }
        else {
            return new PrepareDataForPackageCreation().execute(request);
        }
    }

    private boolean validateParams(HttpServletRequest request) {

        if (getRequestParam(request, RequestParameterName.PACKAGE_TYPE) == null ||
                getRequestParam(request, RequestParameterName.PACKAGE_SENDER_NAME) == null ||
                getRequestParam(request, RequestParameterName.PACKAGE_GETTER_NAME) == null ||
                getRequestParam(request, RequestParameterName.PACKAGE_ADDRESS) == null ||
                getRequestParam(request, RequestParameterName.PACKAGE_POST_INDEX) == null)
        {
            return false;
        }

        if (getRequestParam(request, RequestParameterName.PACKAGE_TYPE).length() > 45 ||
                getRequestParam(request, RequestParameterName.PACKAGE_SENDER_NAME).length() > 45 ||
                getRequestParam(request, RequestParameterName.PACKAGE_GETTER_NAME).length() > 45 ||
                getRequestParam(request, RequestParameterName.PACKAGE_ADDRESS).length() > 45 ||
                getRequestParam(request, RequestParameterName.PACKAGE_POST_INDEX).length() > 6)
        {
            return false;
        }

        try {
            Integer.parseInt(request.getParameter(RequestParameterName.PACKAGE_POST_INDEX));
        } catch (NumberFormatException e) {
            return false;
        }


        return true;
    }

    private String getRequestParam(HttpServletRequest request, String parameterName){
        return request.getParameter(parameterName);
    }
}
