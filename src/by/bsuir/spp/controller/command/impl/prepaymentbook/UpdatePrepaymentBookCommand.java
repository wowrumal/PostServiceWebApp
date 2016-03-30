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

public class UpdatePrepaymentBookCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        if (validateParams(request)) {
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
            prepaymentBookStatement.setStatementNumber(Integer.parseInt(request.getParameter(RequestParameterName.PREPAYMENT_BOOK_NUMBER)));

            PrepaymentBookDao prepaymentBookDao = MySqlPrepaymentBookDao.getInstance();
            try {
                prepaymentBookDao.update(prepaymentBookStatement);
            } catch (DaoException e) {
                e.printStackTrace();
            }

            return new LoadPrepaymentBooksCommand().execute(request);
        }
        else {
            return new SelectPrepaymentBookCommand().execute(request);
        }
    }

    private boolean validateParams(HttpServletRequest request) {

        if (getRequestParam(request, RequestParameterName.CLIENT_NAME) == null ||
                getRequestParam(request, RequestParameterName.CLIENT_NUMBER) == null ||
                getRequestParam(request, RequestParameterName.UNPAID_COST) == null ||
                getRequestParam(request, RequestParameterName.ORGANIZATION_HEAD_NAME) == null ||
                getRequestParam(request, RequestParameterName.BOOKKEEPER_NAME) == null ||
                getRequestParam(request, RequestParameterName.DATE) == null) {
            return false;
        }

        if (getRequestParam(request, RequestParameterName.CLIENT_NAME).length() > 45 ||
                getRequestParam(request, RequestParameterName.CLIENT_NUMBER).length() > 11 ||
                getRequestParam(request, RequestParameterName.UNPAID_COST).length() > 45 ||
                getRequestParam(request, RequestParameterName.ORGANIZATION_HEAD_NAME).length() > 45 ||
                getRequestParam(request, RequestParameterName.BOOKKEEPER_NAME).length() > 45) {
            return false;
        }

        try {
            Integer.parseInt(request.getParameter(RequestParameterName.CLIENT_NUMBER));
            Integer.parseInt(request.getParameter(RequestParameterName.UNPAID_COST));
        } catch (NumberFormatException e) {
            return false;
        }

        try {
            new SimpleDateFormat("yyyy-MM-dd").parse(getRequestParam(request, RequestParameterName.DATE));
        } catch (ParseException e) {
            return false;
        }

        return true;
    }

    private String getRequestParam(HttpServletRequest request, String parameterName) {
        return request.getParameter(parameterName);
    }
}
