package by.bsuir.spp.controller.command.impl.prepaymentbook;

import by.bsuir.spp.bean.document.PrepaymentBookStatement;
import by.bsuir.spp.controller.command.Command;
import by.bsuir.spp.controller.constant.RequestParameterName;
import by.bsuir.spp.dao.PrepaymentBookDao;
import by.bsuir.spp.dao.impl.MySqlPrepaymentBookDao;
import by.bsuir.spp.exception.controller.command.CommandException;
import by.bsuir.spp.exception.dao.DaoException;

import javax.servlet.http.HttpServletRequest;

public class DeletePrepaymentBookCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        int bookId = Integer.parseInt(request.getParameter(RequestParameterName.PREPAYMENT_BOOK_NUMBER));

        PrepaymentBookDao prepaymentBookDao = MySqlPrepaymentBookDao.getInstance();
        PrepaymentBookStatement bookStatement = new PrepaymentBookStatement();
        bookStatement.setStatementNumber(bookId);
        try {
            prepaymentBookDao.delete(bookStatement);
        } catch (DaoException e) {
            e.printStackTrace();
        }

        return new LoadPrepaymentBooksCommand().execute(request);
    }
}
