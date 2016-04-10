package by.bsuir.spp.controller.command.impl.searchstatement;

import by.bsuir.spp.bean.document.SearchPackageStatement;
import by.bsuir.spp.controller.command.DocumentCommand;
import by.bsuir.spp.controller.constant.RequestParameterName;
import by.bsuir.spp.dao.PackageDao;
import by.bsuir.spp.dao.SearchStatementDao;
import by.bsuir.spp.dao.UserDao;
import by.bsuir.spp.dao.impl.MySqlPackageDao;
import by.bsuir.spp.dao.impl.MySqlSearchStatementDao;
import by.bsuir.spp.dao.impl.MySqlUserDao;
import by.bsuir.spp.documentgenerator.DocumentGenerator;
import by.bsuir.spp.documentgenerator.impl.CsvDocumentGenerator;
import by.bsuir.spp.documentgenerator.impl.PdfDocumentGenerator;
import by.bsuir.spp.documentgenerator.impl.XlsDocumentGenerator;
import by.bsuir.spp.exception.dao.DaoException;
import com.itextpdf.text.DocumentException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DownloadSearchPackStatementCommand implements DocumentCommand {

    private static final DocumentCommand instance = new DownloadSearchPackStatementCommand();

    private DownloadSearchPackStatementCommand() {

    }

    public static DocumentCommand getInstance() {
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, DocumentException {
        int statementId = Integer.parseInt(request.getParameter(RequestParameterName.SEARCH_STATEMENT_ID));

        SearchStatementDao searchStatementDao = MySqlSearchStatementDao.getInstance();
        UserDao userDao = MySqlUserDao.getInstance();
        PackageDao packageDao = MySqlPackageDao.getInstance();
        try {
            SearchPackageStatement searchPackageStatement = searchStatementDao.read(statementId);
            searchPackageStatement.setPostPackage(packageDao.read(searchPackageStatement.getPostPackage().getIdPackage()));
            searchPackageStatement.getPostPackage().setGetterUser(userDao.read(searchPackageStatement.getPostPackage().getGetterUser().getId()));
            String fileName = "search_pack_" + searchPackageStatement.getId();

            String docType = request.getParameter(RequestParameterName.DOC_TYPE);
            DocumentGenerator documentGenerator;
            switch (docType) {
                case "pdf" : {
                    fileName += ".pdf";
                    response.setContentType("application/pdf");
                    response.setHeader("Content-Disposition",
                            "attachment;filename=" + fileName);
                    PdfDocumentGenerator pdfDocumentGenerator = (PdfDocumentGenerator) PdfDocumentGenerator.getInstance();
                    pdfDocumentGenerator.setIMAGE_BACK(request.getServletContext().getRealPath("/image/background.jpg"));
                    pdfDocumentGenerator.generateSearchStatement(searchPackageStatement, response.getOutputStream());
                    break;
                }
                case "xls" : {
                    fileName += ".xls";
                    response.setContentType("application/vnd.ms-excel");
                    response.setHeader("Content-Disposition",
                            "attachment;filename=" + fileName);
                    documentGenerator = XlsDocumentGenerator.getInstance();
                    documentGenerator.generateSearchStatement(searchPackageStatement, response.getOutputStream());
                    break;
                }
                case "csv" : {
                    fileName += ".csv";
                    response.setContentType("text/csv");
                    response.setHeader("Content-Disposition",
                            "attachment;filename=" + fileName);
                    documentGenerator = CsvDocumentGenerator.getInstance();
                    documentGenerator.generateSearchStatement(searchPackageStatement, response.getOutputStream());
                    break;
                }
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }

        return "controller?command=select_search_statement&search_statement_id=" + statementId;
    }
}
