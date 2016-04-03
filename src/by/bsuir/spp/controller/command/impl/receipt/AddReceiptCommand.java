package by.bsuir.spp.controller.command.impl.receipt;

import by.bsuir.spp.bean.User;
import by.bsuir.spp.bean.document.Receipt;
import by.bsuir.spp.controller.command.Command;
import by.bsuir.spp.controller.constant.JspPageName;
import by.bsuir.spp.controller.constant.RequestParameterName;
import by.bsuir.spp.dao.ReceiptDao;
import by.bsuir.spp.dao.impl.MySqlReceiptDao;
import by.bsuir.spp.exception.controller.command.CommandException;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class AddReceiptCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        ReceiptDao receiptDao = MySqlReceiptDao.getInstance();
        Receipt receipt = getReceipt(request);
        request.getSession().setAttribute(RequestParameterName.RECEIPT, receipt);
        if (validateParams(request)) {
            /*try {
                receiptDao.create(receipt);
            } catch (DaoException e) {
                e.printStackTrace();
            }*/
            //return new GetUserReceiptsCommand().execute(request);
            return JspPageName.PAYMENT_PAGE;
        }
        else {
            request.getSession().removeAttribute(RequestParameterName.RECEIPT);
            return "controller?command=prepare_data_for_creation_receipt";
        }
    }

    private Receipt getReceipt(HttpServletRequest request) {
        Receipt receipt = new Receipt();
        receipt.setUserId(((User) request.getSession().getAttribute(RequestParameterName.USER)).getId());
        receipt.setClientName(request.getParameter(RequestParameterName.RECEIPT_CLIENTNAME));
        receipt.setCost(Integer.parseInt(request.getParameter(RequestParameterName.RECEIPT_COST)));
        receipt.setPaymentData(request.getParameter(RequestParameterName.RECEIPT_PAYMENT_DATA));
        receipt.setDate(new Date());
        receipt.setServiceName(request.getParameter(RequestParameterName.RECEIPT_SERVICE));

        return receipt;
    }

    private boolean validateParams(HttpServletRequest request) {

        if (getRequestParam(request, RequestParameterName.RECEIPT_CLIENTNAME) == null ||
                getRequestParam(request, RequestParameterName.RECEIPT_PAYMENT_DATA) == null ||
                getRequestParam(request, RequestParameterName.RECEIPT_COST) == null ||
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
            Integer.parseInt(request.getParameter(RequestParameterName.RECEIPT_COST));
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    private String getRequestParam(HttpServletRequest request, String parameterName) {
        return request.getParameter(parameterName);
    }
}
