package by.bsuir.spp.controller.command.impl;

import by.bsuir.spp.bean.Passport;
import by.bsuir.spp.controller.command.Command;
import by.bsuir.spp.controller.constant.JspPageName;
import by.bsuir.spp.controller.constant.RequestParameterName;
import by.bsuir.spp.dao.connectionpool.DBParameter;
import by.bsuir.spp.dao.connectionpool.DBResourceManager;
import by.bsuir.spp.exception.controller.command.CommandException;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TestDataBaseCommand implements Command {
    private static final String SQL_SELECT = "select * from passport";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        List<Passport> passports = new ArrayList<>();
        try(Connection connection = establishConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT);
            while (resultSet.next()) {
                Passport passport = new Passport();
                passport.setPassportNumber(resultSet.getString(2));
                passport.setAddress(resultSet.getString(3));
                passport.setIssuingInsitution(resultSet.getString(4));
                passport.setIssueDate(new Date(resultSet.getTimestamp(5).getTime()));
                passports.add(passport);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute(RequestParameterName.DB_CONTENT, passports);

        return JspPageName.INDEX;
    }

    private Connection establishConnection() {
        DBResourceManager dbResourceManager = DBResourceManager.getInstance();
        String driverName = dbResourceManager.getValue(DBParameter.DB_DRIVER);
        String url = dbResourceManager.getValue(DBParameter.DB_URL);
        String user = dbResourceManager.getValue(DBParameter.DB_USER);
        String password = dbResourceManager.getValue(DBParameter.DB_PASSWORD);

        Locale.setDefault(Locale.ENGLISH);
        Connection connection = null;
        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
