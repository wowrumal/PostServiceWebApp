package by.bsuir.spp.controller.command.impl.prepaymentbook;

import by.bsuir.spp.bean.document.PrepaymentBookStatement;
import by.bsuir.spp.controller.command.Command;
import by.bsuir.spp.controller.constant.JspPageName;
import by.bsuir.spp.controller.constant.RequestParameterName;
import by.bsuir.spp.dao.PrepaymentBookDao;
import by.bsuir.spp.dao.impl.MySqlPrepaymentBookDao;
import by.bsuir.spp.exception.controller.command.CommandException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class LoadPrepaymentBooksCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        PrepaymentBookDao prepaymentBookDao = MySqlPrepaymentBookDao.getInstance();

        List<PrepaymentBookStatement> prepaymentBooks = prepaymentBookDao.getAllPrepaymentBooks();
        request.setAttribute(RequestParameterName.PREPAYMENT_BOOKS, prepaymentBooks);

        return JspPageName.PREPAYMENT_BOOKS;
    }
}
