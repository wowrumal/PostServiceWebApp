package by.bsuir.spp.controller.command.impl.receipt;

import by.bsuir.spp.bean.document.Receipt;
import by.bsuir.spp.controller.command.Command;
import by.bsuir.spp.controller.constant.JspPageName;
import by.bsuir.spp.controller.constant.RequestParameterName;
import by.bsuir.spp.dao.ReceiptDao;
import by.bsuir.spp.dao.impl.MySqlReceiptDao;
import by.bsuir.spp.exception.controller.command.CommandException;
import by.bsuir.spp.exception.dao.DaoException;

import javax.servlet.http.HttpServletRequest;

public class PaymentCommand implements Command {

    private static final PaymentCommand instance = new PaymentCommand();

    private static int COUNTER = 1;

    private PaymentCommand() {

    }

    public static PaymentCommand getInstance() {
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        ReceiptDao receiptDao = MySqlReceiptDao.getInstance();

        try {
            if (COUNTER != 100) {
                Receipt receipt = (Receipt) request.getSession().getAttribute(RequestParameterName.RECEIPT);
                receiptDao.create(receipt);
                COUNTER++;
                request.getSession().removeAttribute(RequestParameterName.RECEIPT);
                return new GetUserReceiptsCommand().execute(request);
            }
            else {
                COUNTER = 1;
                return JspPageName.VIEW_RECEIPT + "?" + RequestParameterName.MESSAGE + "=error";
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }

        return JspPageName.VIEW_RECEIPT;
    }
}
