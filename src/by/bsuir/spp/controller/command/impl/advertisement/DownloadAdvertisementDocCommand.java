package by.bsuir.spp.controller.command.impl.advertisement;

import by.bsuir.spp.bean.Passport;
import by.bsuir.spp.bean.User;
import by.bsuir.spp.bean.document.Advertisement;
import by.bsuir.spp.controller.command.DocumentCommand;
import by.bsuir.spp.controller.constant.RequestParameterName;
import by.bsuir.spp.dao.AdvertisementDao;
import by.bsuir.spp.dao.PackageDao;
import by.bsuir.spp.dao.PassportDao;
import by.bsuir.spp.dao.UserDao;
import by.bsuir.spp.dao.impl.MySqlAdvertisementDao;
import by.bsuir.spp.dao.impl.MySqlPackageDao;
import by.bsuir.spp.dao.impl.MySqlPassportDao;
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

public class DownloadAdvertisementDocCommand implements DocumentCommand {

    private static final DocumentCommand instance = new DownloadAdvertisementDocCommand();

    private DownloadAdvertisementDocCommand() {}

    public static DocumentCommand getInstance() {
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, DocumentException {
        AdvertisementDao advertisementDao = MySqlAdvertisementDao.getInstance();
        PackageDao packageDao = MySqlPackageDao.getInstance();
        PassportDao passportDao = MySqlPassportDao.getInstance();
        UserDao userDao = MySqlUserDao.getInstance();

        int idPackageOfAdvertisement = Integer.parseInt(request.getParameter(RequestParameterName.PACKAGE_ID));
        try {
            by.bsuir.spp.bean.document.Package packagee = packageDao.read(
                    Integer.parseInt(request.getParameter(RequestParameterName.PACKAGE_ID)));
            User user = userDao.read(packagee.getGetterUser().getId());
            packagee.setGetterUser(user);
            Passport passport = passportDao.read(packagee.getPassportId());
            Advertisement advertisement = advertisementDao.read(idPackageOfAdvertisement);
            advertisement.setPassport(passport);
            advertisement.setPostPackage(packagee);

            String fileName = "advertisement" + advertisement.getPostPackage().getIdPackage();

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
                    pdfDocumentGenerator.generateAdvertisement(advertisement, response.getOutputStream());
                    break;
                }
                case "xls" : {
                    fileName += ".xls";
                    response.setContentType("application/vnd.ms-excel");
                    response.setHeader("Content-Disposition",
                            "attachment;filename=" + fileName);
                    documentGenerator = XlsDocumentGenerator.getInstance();
                    documentGenerator.generateAdvertisement(advertisement, response.getOutputStream());
                    break;
                }
                case "csv" : {
                    fileName += ".csv";
                    response.setContentType("text/csv");
                    response.setHeader("Content-Disposition",
                            "attachment;filename=" + fileName);
                    documentGenerator = CsvDocumentGenerator.getInstance();
                    documentGenerator.generateAdvertisement(advertisement, response.getOutputStream());
                    break;
                }
            }

        } catch (DaoException e) {
            e.printStackTrace();
        }

        return "controller?command=select_advertisement&package_id=" + idPackageOfAdvertisement;
    }
}
