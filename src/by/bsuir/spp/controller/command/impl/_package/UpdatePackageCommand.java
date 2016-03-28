package by.bsuir.spp.controller.command.impl._package;

import by.bsuir.spp.bean.User;
import by.bsuir.spp.bean.document.Package;
import by.bsuir.spp.controller.command.Command;
import by.bsuir.spp.controller.constant.RequestParameterName;
import by.bsuir.spp.dao.PackageDao;
import by.bsuir.spp.dao.impl.MySqlPackageDao;
import by.bsuir.spp.exception.controller.command.CommandException;
import by.bsuir.spp.exception.dao.DaoException;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Кирилл on 2/21/2016.
 */
public class UpdatePackageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        if (validateParams(request)) {

            Package myPackage = new Package();

            myPackage.setIdPackage(Integer.parseInt(request.getParameter(RequestParameterName.PACKAGE_ID)));
            myPackage.setType(request.getParameter(RequestParameterName.PACKAGE_TYPE));

            try {
                myPackage.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter(RequestParameterName.PACKAGE_DATE)));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            myPackage.setSenderName(request.getParameter(RequestParameterName.PACKAGE_SENDER_NAME));
            myPackage.setGetterUser(new User() {{
                setId(Integer.parseInt(request.getParameter(RequestParameterName.PACKAGE_GETTER_NAME)));
            }});
            myPackage.setAddress(request.getParameter(RequestParameterName.PACKAGE_ADDRESS));
            myPackage.setPostIndex(Integer.parseInt(request.getParameter(RequestParameterName.PACKAGE_POST_INDEX)));
            myPackage.setBarCode(Integer.parseInt(request.getParameter(RequestParameterName.PACKAGE_BARCODE)));

            PackageDao packageDao = MySqlPackageDao.getInstance();
            try {
                packageDao.update(myPackage);
            } catch (DaoException e) {
                e.printStackTrace();
            }

            return new LoadPackagesCommand().execute(request);
        }
        else {
            return new SelectPackageCommand().execute(request);
        }
    }

    private boolean validateParams(HttpServletRequest request) {

        if (getRequestParam(request, RequestParameterName.PACKAGE_TYPE) == null ||
                getRequestParam(request, RequestParameterName.PACKAGE_DATE) == null ||
                getRequestParam(request, RequestParameterName.PACKAGE_SENDER_NAME) == null ||
                getRequestParam(request, RequestParameterName.PACKAGE_GETTER_NAME) == null ||
                getRequestParam(request, RequestParameterName.PACKAGE_ADDRESS) == null ||
                getRequestParam(request, RequestParameterName.PACKAGE_POST_INDEX) == null ||
                getRequestParam(request, RequestParameterName.PACKAGE_BARCODE) == null) {
            return false;
        }

        if (getRequestParam(request, RequestParameterName.PACKAGE_TYPE).length() > 45 ||
                getRequestParam(request, RequestParameterName.PACKAGE_SENDER_NAME).length() > 45 ||
                getRequestParam(request, RequestParameterName.PACKAGE_GETTER_NAME).length() > 45 ||
                getRequestParam(request, RequestParameterName.PACKAGE_ADDRESS).length() > 45 ||
                getRequestParam(request, RequestParameterName.PACKAGE_POST_INDEX).length() > 45 ||
                getRequestParam(request, RequestParameterName.PACKAGE_BARCODE).length() > 45) {
            return false;
        }

        try {
            new SimpleDateFormat("yyyy-MM-dd").parse(getRequestParam(request, RequestParameterName.PACKAGE_DATE));
        } catch (ParseException e) {
            return false;
        }

        return true;
    }

    private String getRequestParam(HttpServletRequest request, String parameterName){
        return request.getParameter(parameterName);
    }
}
