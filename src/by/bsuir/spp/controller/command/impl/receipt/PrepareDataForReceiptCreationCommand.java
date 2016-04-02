package by.bsuir.spp.controller.command.impl.receipt;

import by.bsuir.spp.controller.command.Command;
import by.bsuir.spp.controller.constant.JspPageName;
import by.bsuir.spp.controller.constant.RequestParameterName;
import by.bsuir.spp.dao.UserDao;
import by.bsuir.spp.dao.impl.MySqlUserDao;
import by.bsuir.spp.exception.controller.command.CommandException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class PrepareDataForReceiptCreationCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        UserDao userDao = MySqlUserDao.getInstance();
        List<Integer> userIds = userDao.getUserIds();

        request.setAttribute(RequestParameterName.USER_IDS, userIds);
        return JspPageName.RECEIPT;
    }
}
