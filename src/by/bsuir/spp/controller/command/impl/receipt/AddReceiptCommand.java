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

        Receipt receipt = new Receipt();
        receipt.setUserId(((User)request.getSession().getAttribute(RequestParameterName.USER)).getId());
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
}
