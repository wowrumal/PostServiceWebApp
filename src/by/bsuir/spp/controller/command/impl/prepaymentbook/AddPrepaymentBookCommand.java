package by.bsuir.spp.controller.command.impl.prepaymentbook;

import by.bsuir.spp.bean.document.PrepaymentBookStatement;
import by.bsuir.spp.controller.command.Command;
import by.bsuir.spp.controller.constant.RequestParameterName;
import by.bsuir.spp.dao.PrepaymentBookDao;
import by.bsuir.spp.dao.impl.MySqlPrepaymentBookDao;
import by.bsuir.spp.exception.controller.command.CommandException;
import by.bsuir.spp.exception.dao.DaoException;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class AddPrepaymentBookCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        PrepaymentBookStatement statement = getStatement(request);
        request.getSession().setAttribute("prepayment_book", statement);

        if (validateParams(request)) {

            PrepaymentBookDao prepaymentBookDao = MySqlPrepaymentBookDao.getInstance();
            try {
                prepaymentBookDao.create(statement);
                request.getSession().removeAttribute("prepayment_book");
            } catch (DaoException e) {
                e.printStackTrace();
            }

            return "controller?command=get_user_prepayment_books";
        }
        else {
            return "controller?command=prepare_data_for_creation_prepayment_book";
        }
    }

    private PrepaymentBookStatement getStatement(HttpServletRequest request) {
        PrepaymentBookStatement prepaymentBookStatement = new PrepaymentBookStatement();
        prepaymentBookStatement.setPassportId(Integer.parseInt(request.getParameter(RequestParameterName.PASSPORT_ID)));
        prepaymentBookStatement.setBookkeeperName(request.getParameter(RequestParameterName.BOOKKEEPER_NAME));
        prepaymentBookStatement.setClientName(request.getParameter(RequestParameterName.CLIENT_NAME));
        prepaymentBookStatement.setClientNumber(Integer.parseInt(request.getParameter(RequestParameterName.CLIENT_NUMBER)));
        prepaymentBookStatement.setDate(new Date());

        prepaymentBookStatement.setUnpaidCost(Integer.parseInt(request.getParameter(RequestParameterName.UNPAID_COST)));
        prepaymentBookStatement.setOrganizationHeadName(request.getParameter(RequestParameterName.ORGANIZATION_HEAD_NAME));

        return prepaymentBookStatement;
    }

    private boolean validateParams(HttpServletRequest request) {

        if (getRequestParam(request, RequestParameterName.CLIENT_NAME) == null ||
                getRequestParam(request, RequestParameterName.CLIENT_NUMBER) == null ||
                getRequestParam(request, RequestParameterName.UNPAID_COST) == null ||
                getRequestParam(request, RequestParameterName.ORGANIZATION_HEAD_NAME) == null ||
                getRequestParam(request, RequestParameterName.BOOKKEEPER_NAME) == null) {
            return false;
        }

        if (getRequestParam(request, RequestParameterName.CLIENT_NAME).length() > 45 ||
                getRequestParam(request, RequestParameterName.CLIENT_NUMBER).length() > 7 ||
                getRequestParam(request, RequestParameterName.UNPAID_COST).length() > 6 ||
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

        return true;
    }

    private String getRequestParam(HttpServletRequest request, String parameterName) {
        return request.getParameter(parameterName);
    }
}
