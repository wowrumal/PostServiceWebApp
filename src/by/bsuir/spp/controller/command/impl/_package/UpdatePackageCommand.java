package by.bsuir.spp.controller.command.impl._package;

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

       Package myPackage = new Package();

        myPackage.setIdPackage(Integer.parseInt(request.getParameter(RequestParameterName.PACKAGE_ID)));
        myPackage.setType(request.getParameter(RequestParameterName.PACKAGE_TYPE));

        try {
            myPackage.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter(RequestParameterName.PACKAGE_DATE)));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        myPackage.setSenderName(request.getParameter(RequestParameterName.PACKAGE_SENDER_NAME));
        myPackage.setGetterName(request.getParameter(RequestParameterName.PACKAGE_GETTER_NAME));
        myPackage.setAddress(request.getParameter(RequestParameterName.PACKAGE_ADDRESS));
        myPackage.setPostIndex(Integer.parseInt(request.getParameter(RequestParameterName.PACKAGE_POST_INDEX)));
        myPackage.setBarCode(Integer.parseInt(request.getParameter(RequestParameterName.PACKAGE_BARCODE)));

        PackageDao packageDao = MySqlPackageDao.getInstance();
        try{
            packageDao.update(myPackage);
        } catch (DaoException e) {
            e.printStackTrace();
        }

        return new LoadPackagesCommand().execute(request);
    }
}
