package by.bsuir.spp.controller;

import by.bsuir.spp.controller.command.impl._package.DownloadPackageDocCommand;
import by.bsuir.spp.controller.command.impl.advertisement.DownloadAdvertisementDocCommand;
import by.bsuir.spp.controller.command.impl.prepaymentbook.DownloadPrepaymentBookDocCommand;
import by.bsuir.spp.controller.command.impl.receipt.DownloadReceiptDocCommand;
import by.bsuir.spp.controller.command.impl.searchstatement.DownloadSearchPackStatementCommand;
import by.bsuir.spp.controller.constant.RequestParameterName;
import com.itextpdf.text.DocumentException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@MultipartConfig
public class DocumentDownloadController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String commandName = req.getParameter(RequestParameterName.COMMAND_NAME);
        String page = null;
        try {
            switch (commandName) {
                case "package_document" :{
                    page = DownloadPackageDocCommand.getInstance().execute(req, resp);
                    break;
                }
                case "advertisement_document" : {
                    page = DownloadAdvertisementDocCommand.getInstance().execute(req, resp);
                    break;
                }
                case "prepayment_book_document" : {
                    page = DownloadPrepaymentBookDocCommand.getInstance().execute(req, resp);
                    break;
                }
                case "receipt_document" : {
                    page = DownloadReceiptDocCommand.getInstance().execute(req, resp);
                    break;
                }
                case "search_document" : {
                    page = DownloadSearchPackStatementCommand.getInstance().execute(req, resp);
                    break;
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher(page);
        dispatcher.forward(req, resp);
    }
}
