package by.bsuir.spp.controller.command;

import com.itextpdf.text.DocumentException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface DocumentCommand {
    String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, DocumentException;
}
