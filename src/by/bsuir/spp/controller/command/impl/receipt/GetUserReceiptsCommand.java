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
import java.util.List;

public class GetUserReceiptsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = JspPageName.LOGIN_PAGE;

        try {
            int userId = ((User) request.getSession().getAttribute(RequestParameterName.USER)).getId();
            ReceiptDao receiptDao = MySqlReceiptDao.getInstance();
            List<Receipt> receiptList = receiptDao.getUserReceipts(userId);
            request.setAttribute(RequestParameterName.RECEIPTS, receiptList);
            page = JspPageName.USER_RECEIPTS;
        } catch (Exception e ) {
            e.printStackTrace();
        }

        return page;
    }
}
