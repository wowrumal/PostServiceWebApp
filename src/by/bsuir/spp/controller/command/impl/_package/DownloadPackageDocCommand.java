package by.bsuir.spp.controller.command.impl._package;

import by.bsuir.spp.controller.command.DocumentCommand;
import by.bsuir.spp.controller.constant.RequestParameterName;
import by.bsuir.spp.dao.PackageDao;
import by.bsuir.spp.dao.UserDao;
import by.bsuir.spp.dao.impl.MySqlPackageDao;
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
import java.net.URLEncoder;

public class DownloadPackageDocCommand implements DocumentCommand {

    private static final DocumentCommand instance = new DownloadPackageDocCommand();

    private DownloadPackageDocCommand() {

    }

    public static DocumentCommand getInstance() {
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, DocumentException {
        int packageId = Integer.parseInt(request.getParameter(RequestParameterName.PACKAGE_ID));

        PackageDao packageDao = MySqlPackageDao.getInstance();
        UserDao userDao = MySqlUserDao.getInstance();
        by.bsuir.spp.bean.document.Package myPackage = null;

        try {
            myPackage = packageDao.read(packageId);
            myPackage.setGetterUser(userDao.read(myPackage.getGetterUser().getId()));
        } catch (DaoException e) {
            e.printStackTrace();
        }

        String fileName = "Package_" + myPackage.getSenderName();
        fileName = URLEncoder.encode(fileName, "UTF-8");

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
                pdfDocumentGenerator.generatePackage(myPackage, response.getOutputStream());
                break;
            }
            case "xls" : {
                fileName += ".xls";
                response.setContentType("application/vnd.ms-excel");
                response.setHeader("Content-Disposition",
                        "attachment;filename=" + fileName);
                documentGenerator = XlsDocumentGenerator.getInstance();
                documentGenerator.generatePackage(myPackage, response.getOutputStream());
                break;
            }
            case "csv" : {
                fileName += ".csv";
                response.setContentType("text/csv");
                response.setHeader("Content-Disposition",
                        "attachment;filename=" + fileName);
                documentGenerator = CsvDocumentGenerator.getInstance();
                documentGenerator.generatePackage(myPackage, response.getOutputStream());
                break;
            }
        }

        return "controller?command=PREPARE_DATA_FOR_CREATION_PACKAGE&package_id=" + packageId;
    }
}
