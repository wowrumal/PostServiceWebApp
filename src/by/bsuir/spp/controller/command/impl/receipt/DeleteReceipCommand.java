package by.bsuir.spp.controller.command.impl.receipt;

import by.bsuir.spp.bean.document.Receipt;
import by.bsuir.spp.controller.command.Command;
import by.bsuir.spp.controller.constant.RequestParameterName;
import by.bsuir.spp.dao.ReceiptDao;
import by.bsuir.spp.dao.impl.MySqlReceiptDao;
import by.bsuir.spp.exception.controller.command.CommandException;
import by.bsuir.spp.exception.dao.DaoException;

import javax.servlet.http.HttpServletRequest;

public class DeleteReceipCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        int idReceipt = Integer.parseInt(request.getParameter(RequestParameterName.RECEIPT_ID));
        ReceiptDao receiptDao = MySqlReceiptDao.getInstance();
        Receipt receipt = new Receipt();
        receipt.setReceiptId(idReceipt);
        try {
            receiptDao.delete(receipt);
        } catch (DaoException e) {
            e.printStackTrace();
        }

        return new LoadReceiptsCommand().execute(request);
    }
}
