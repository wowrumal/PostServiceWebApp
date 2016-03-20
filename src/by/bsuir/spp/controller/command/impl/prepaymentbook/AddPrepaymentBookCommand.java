package by.bsuir.spp.controller.command.impl.prepaymentbook;

import by.bsuir.spp.bean.document.PrepaymentBookStatement;
import by.bsuir.spp.controller.command.Command;
import by.bsuir.spp.controller.constant.RequestParameterName;
import by.bsuir.spp.dao.PrepaymentBookDao;
import by.bsuir.spp.dao.impl.MySqlPrepaymentBookDao;
import by.bsuir.spp.exception.controller.command.CommandException;
import by.bsuir.spp.exception.dao.DaoException;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AddPrepaymentBookCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        PrepaymentBookStatement prepaymentBookStatement = new PrepaymentBookStatement();
        prepaymentBookStatement.setPassportId(Integer.parseInt(request.getParameter(RequestParameterName.PASSPORT_ID)));
        prepaymentBookStatement.setBookkeeperName(request.getParameter(RequestParameterName.BOOKKEEPER_NAME));
        prepaymentBookStatement.setClientName(request.getParameter(RequestParameterName.CLIENT_NAME));
        prepaymentBookStatement.setClientNumber(Integer.parseInt(request.getParameter(RequestParameterName.CLIENT_NUMBER)));
        try {
            prepaymentBookStatement.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter(RequestParameterName.DATE)));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        prepaymentBookStatement.setUnpaidCost(Integer.parseInt(request.getParameter(RequestParameterName.UNPAID_COST)));
        prepaymentBookStatement.setOrganizationHeadName(request.getParameter(RequestParameterName.ORGANIZATION_HEAD_NAME));

        PrepaymentBookDao prepaymentBookDao = MySqlPrepaymentBookDao.getInstance();
        try {
            prepaymentBookDao.create(prepaymentBookStatement);
        } catch (DaoException e) {
            e.printStackTrace();
        }

        return new GetUserPrepaymentBooksCommand().execute(request);
    }
}
