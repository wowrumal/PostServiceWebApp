package by.bsuir.spp.controller.command.impl._package;

import by.bsuir.spp.bean.Comment;
import by.bsuir.spp.controller.command.Command;
import by.bsuir.spp.controller.constant.JspPageName;
import by.bsuir.spp.controller.constant.RequestParameterName;
import by.bsuir.spp.dao.PackageCommentDao;
import by.bsuir.spp.dao.PackageDao;
import by.bsuir.spp.dao.UserDao;
import by.bsuir.spp.dao.impl.MySqlPackageCommentDao;
import by.bsuir.spp.dao.impl.MySqlPackageDao;
import by.bsuir.spp.dao.impl.MySqlUserDao;
import by.bsuir.spp.exception.controller.command.CommandException;
import by.bsuir.spp.exception.dao.DaoException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Кирилл on 2/21/2016.
 */
public class SelectPackageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        int packageId = Integer.parseInt(request.getParameter(RequestParameterName.PACKAGE_ID));

        PackageDao packageDao = MySqlPackageDao.getInstance();
        UserDao userDao = MySqlUserDao.getInstance();
        PackageCommentDao packageCommentDao = MySqlPackageCommentDao.getInstance();
        by.bsuir.spp.bean.document.Package myPackage = null;

        try {
            List<Comment> commentList = packageCommentDao.getCommentsForPackage(packageId);
            request.setAttribute(RequestParameterName.COMMENTS, commentList);
            myPackage = packageDao.read(packageId);
            myPackage.setGetterUser(userDao.read(myPackage.getGetterUser().getId()));
        } catch (DaoException e) {
            e.printStackTrace();
        }

        if(myPackage != null) {
            request.setAttribute(RequestParameterName.PACKAGE, myPackage);
        }

        return JspPageName.PACKAGE;
    }
}
