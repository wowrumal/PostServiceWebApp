package by.bsuir.spp.controller.command.impl._package;

import by.bsuir.spp.bean.Comment;
import by.bsuir.spp.controller.command.Command;
import by.bsuir.spp.controller.command.CommandName;
import by.bsuir.spp.controller.constant.RequestParameterName;
import by.bsuir.spp.dao.PackageCommentDao;
import by.bsuir.spp.dao.impl.MySqlPackageCommentDao;
import by.bsuir.spp.exception.controller.command.CommandException;
import by.bsuir.spp.exception.dao.DaoException;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class AddCommentCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        int packageId= Integer.parseInt(request.getParameter(RequestParameterName.PACKAGE_ID));

        PackageCommentDao packageCommentDao = MySqlPackageCommentDao.getInstance();

        Comment comment = new Comment();
        comment.setPackageId(packageId);
        comment.setText(request.getParameter(RequestParameterName.COMMENT_TEXT));
        comment.setDate(new Date());

        try {
            packageCommentDao.create(comment);
        } catch (DaoException e) {
            e.printStackTrace();
        }

        return "controller?command=" + CommandName.SELECT_PACKAGE.toString().toLowerCase() + "&package_id="+packageId;
    }
}
