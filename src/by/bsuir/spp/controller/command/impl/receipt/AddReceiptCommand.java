package by.bsuir.spp.controller.command.impl.receipt;

import by.bsuir.spp.bean.User;
import by.bsuir.spp.bean.document.Receipt;
import by.bsuir.spp.controller.command.Command;
import by.bsuir.spp.controller.constant.RequestParameterName;
import by.bsuir.spp.dao.ReceiptDao;
import by.bsuir.spp.dao.impl.MySqlReceiptDao;
import by.bsuir.spp.exception.controller.command.CommandException;
import by.bsuir.spp.exception.dao.DaoException;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AddReceiptCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        ReceiptDao receiptDao = MySqlReceiptDao.getInstance();

        if (validateParams(request)) {

            Receipt receipt = new Receipt();
            receipt.setUserId(((User) request.getSession().getAttribute(RequestParameterName.USER)).getId());
            receipt.setClientName(request.getParameter(RequestParameterName.RECEIPT_CLIENTNAME));
            receipt.setCost(Integer.parseInt(request.getParameter(RequestParameterName.RECEIPT_COST)));
            receipt.setPaymentData(request.getParameter(RequestParameterName.RECEIPT_PAYMENT_DATA));
            try {
                receipt.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter(RequestParameterName.RECEIPT_DATE)));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            receipt.setServiceName(request.getParameter(RequestParameterName.RECEIPT_SERVICE));

            try {
                receiptDao.create(receipt);
            } catch (DaoException e) {
                e.printStackTrace();
            }

            return new GetUserReceiptsCommand().execute(request);
        }
        else {
            return new PrepareDataForReceiptCreationCommand().execute(request);
        }
    }

    private boolean validateParams(HttpServletRequest request) {

        if (getRequestParam(request, RequestParameterName.RECEIPT_CLIENTNAME) == null ||
                getRequestParam(request, RequestParameterName.RECEIPT_PAYMENT_DATA) == null ||
                getRequestParam(request, RequestParameterName.RECEIPT_COST) == null ||
                getRequestParam(request, RequestParameterName.RECEIPT_DATE) == null ||
                getRequestParam(request, RequestParameterName.RECEIPT_SERVICE) == null) {
            return false;
        }

        if (getRequestParam(request, RequestParameterName.RECEIPT_CLIENTNAME).length() > 45 ||
                getRequestParam(request, RequestParameterName.RECEIPT_PAYMENT_DATA).length() > 255 ||
                getRequestParam(request, RequestParameterName.RECEIPT_SERVICE).length() > 255 ||
                getRequestParam(request, RequestParameterName.RECEIPT_COST).length() > 15) {
            return false;
        }

        try {
            new SimpleDateFormat("yyyy-MM-dd").parse(getRequestParam(request, RequestParameterName.RECEIPT_DATE));
        } catch (ParseException e) {
            return false;
        }

        return true;
    }

    private String getRequestParam(HttpServletRequest request, String parameterName) {
        return request.getParameter(parameterName);
    }
}
