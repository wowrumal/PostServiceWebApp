package by.bsuir.spp.controller.command.impl.passport;

import by.bsuir.spp.bean.Passport;
import by.bsuir.spp.controller.command.Command;
import by.bsuir.spp.controller.constant.JspPageName;
import by.bsuir.spp.controller.constant.RequestParameterName;
import by.bsuir.spp.dao.impl.MySqlPassportDao;
import by.bsuir.spp.exception.controller.command.CommandException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class LoadPassportsCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        List<Passport> passports = MySqlPassportDao.getInstance().getAllPassports();
        request.setAttribute(RequestParameterName.PASSPORTS, passports);

        return JspPageName.PASSPORTS;
    }
}
