package by.bsuir.spp.controller.command.impl.prepaymentbook;

import by.bsuir.spp.bean.User;
import by.bsuir.spp.bean.document.PrepaymentBookStatement;
import by.bsuir.spp.controller.command.Command;
import by.bsuir.spp.controller.constant.JspPageName;
import by.bsuir.spp.controller.constant.RequestParameterName;
import by.bsuir.spp.dao.PrepaymentBookDao;
import by.bsuir.spp.dao.impl.MySqlPrepaymentBookDao;
import by.bsuir.spp.exception.controller.command.CommandException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetUserPrepaymentBooksCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page;

        try {
            PrepaymentBookDao prepaymentBookDao = MySqlPrepaymentBookDao.getInstance();

            List<PrepaymentBookStatement> statementList = prepaymentBookDao.getPrepaymentBooksByPassportId(
                    ((User)request.getSession().getAttribute(RequestParameterName.USER)).getPassport().getPassportId());

            request.setAttribute(RequestParameterName.PREPAYMENT_BOOKS, statementList);

            page = JspPageName.USER_PREPAYMENT_BOOKS;
        } catch (Exception e) {
            page = JspPageName.LOGIN_PAGE;
        }

        return page;
    }
}
