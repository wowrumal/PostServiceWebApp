package by.bsuir.spp.controller.command.impl.passport;

import by.bsuir.spp.bean.Passport;
import by.bsuir.spp.controller.command.Command;
import by.bsuir.spp.controller.constant.RequestParameterName;
import by.bsuir.spp.dao.PassportDao;
import by.bsuir.spp.dao.impl.MySqlPassportDao;
import by.bsuir.spp.exception.controller.command.CommandException;
import by.bsuir.spp.exception.dao.DaoException;

import javax.servlet.http.HttpServletRequest;

public class DeletePassportCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        int passportId = Integer.parseInt(request.getParameter(RequestParameterName.PASSPORT_ID));
        Passport passport = new Passport();
        passport.setPassportId(passportId);

        PassportDao passportDao = MySqlPassportDao.getInstance();

        try {
            passportDao.delete(passport);
        } catch (DaoException e) {
            e.printStackTrace();
        }

        return new LoadPassportsCommand().execute(request);
    }
}
