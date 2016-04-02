package by.bsuir.spp.controller.command.impl.prepaymentbook;

import by.bsuir.spp.controller.command.Command;
import by.bsuir.spp.controller.constant.JspPageName;
import by.bsuir.spp.controller.constant.RequestParameterName;
import by.bsuir.spp.dao.PassportDao;
import by.bsuir.spp.dao.impl.MySqlPassportDao;
import by.bsuir.spp.exception.controller.command.CommandException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class PrepareDataForPrepaymentBookCreationCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        PassportDao passportDao = MySqlPassportDao.getInstance();
        List<Integer> passportIds = passportDao.getIdPassports();

        request.setAttribute(RequestParameterName.PASSPORT_IDS, passportIds);

        return JspPageName.PREPAYMENT_BOOK;

    }
}
