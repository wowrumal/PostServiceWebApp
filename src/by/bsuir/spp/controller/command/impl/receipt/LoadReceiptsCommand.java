package by.bsuir.spp.controller.command.impl.receipt;

import by.bsuir.spp.bean.document.Receipt;
import by.bsuir.spp.controller.command.Command;
import by.bsuir.spp.controller.constant.JspPageName;
import by.bsuir.spp.controller.constant.RequestParameterName;
import by.bsuir.spp.dao.ReceiptDao;
import by.bsuir.spp.dao.impl.MySqlReceiptDao;
import by.bsuir.spp.exception.controller.command.CommandException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class LoadReceiptsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        List<Receipt> receipts;
        ReceiptDao receiptDao = MySqlReceiptDao.getInstance();
        receipts = receiptDao.getAllReceipts();
        request.setAttribute(RequestParameterName.RECEIPTS, receipts);
        return JspPageName.RECEIPTS;
    }
}
