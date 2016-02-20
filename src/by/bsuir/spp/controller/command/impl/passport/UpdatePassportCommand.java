package by.bsuir.spp.controller.command.impl.passport;

import by.bsuir.spp.bean.Passport;
import by.bsuir.spp.controller.command.Command;
import by.bsuir.spp.controller.constant.RequestParameterName;
import by.bsuir.spp.dao.PassportDao;
import by.bsuir.spp.dao.impl.MySqlPassportDao;
import by.bsuir.spp.exception.controller.command.CommandException;
import by.bsuir.spp.exception.dao.DaoException;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UpdatePassportCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        Passport passport = new Passport();

        passport.setPassportId(Integer.parseInt(request.getParameter(RequestParameterName.PASSPORT_ID)));
        passport.setPassportNumber(request.getParameter(RequestParameterName.PASSPORT_NUMBER));
        passport.setAddress(request.getParameter(RequestParameterName.PASSPORT_ADDRESS));
        passport.setIssuingInstitution(request.getParameter(RequestParameterName.INSTITUTION));
        try {
            passport.setIssueDate(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter(RequestParameterName.ISSUING_DATE)));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        PassportDao passportDao = MySqlPassportDao.getInstance();
        try {
            passportDao.update(passport);
        } catch (DaoException e) {
            e.printStackTrace();
        }

        return new LoadPassportsCommand().execute(request);

    }
}
