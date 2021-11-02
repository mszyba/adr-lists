package eu.michalszyba.adrlist.controller;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import eu.michalszyba.adrlist.model.Company;
import eu.michalszyba.adrlist.model.Waybill;
import eu.michalszyba.adrlist.service.CompanyService;
import eu.michalszyba.adrlist.service.WaybillService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Controller
public class PdfController {

    private static final String BASE_URI = "http://localhost:8091";

    private final ServletContext servletContext;
    private final TemplateEngine templateEngine;
    private final CompanyService companyService;
    private final WaybillService waybillService;

    public PdfController(ServletContext servletContext, TemplateEngine templateEngine, CompanyService companyService, WaybillService waybillService) {
        this.servletContext = servletContext;
        this.templateEngine = templateEngine;
        this.companyService = companyService;
        this.waybillService = waybillService;
    }

    @RequestMapping(path = "/pdf")
    public ResponseEntity<?> getPDF(HttpServletRequest request, HttpServletResponse response) {

        /* Do Business Logic*/
        List<Company> companies = companyService.getAllCompany();

        Waybill oneWaybill = waybillService.getOneWaybillById(1L);

        /* Create HTML using Thymeleaf template Engine */
        WebContext context = new WebContext(request, response, servletContext);
        context.setVariable("companies", companies);
        context.setVariable("delivery", oneWaybill);
        String html = templateEngine.process("pdf/order-pdf", context);

        /* Setup Source and target I/O streams */
        ByteArrayOutputStream target = new ByteArrayOutputStream();
        ConverterProperties converterProperties = new ConverterProperties();
        converterProperties.setBaseUri(BASE_URI);

        /* Call convert method */
        HtmlConverter.convertToPdf(html, target, converterProperties);

        /* extract output as bytes */
        byte[] bytes = target.toByteArray();

        /* Send the response as downloadable PDF */
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=order.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(bytes);
    }

    @RequestMapping(path = "/waybill/pdf/{id}")
    public ResponseEntity<?> getPDFById(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {

        /* Do Business Logic*/
        List<Company> companies = companyService.getAllCompany();

        Waybill oneWaybill = waybillService.getOneWaybillById(id);

        /* Create HTML using Thymeleaf template Engine */
        WebContext context = new WebContext(request, response, servletContext);
        context.setVariable("companies", companies);
        context.setVariable("waybill", oneWaybill);
        String html = templateEngine.process("pdf/pdf-by-id", context);

        /* Setup Source and target I/O streams */
        ByteArrayOutputStream target = new ByteArrayOutputStream();
        ConverterProperties converterProperties = new ConverterProperties();
        converterProperties.setBaseUri(BASE_URI);

        /* Call convert method */
        HtmlConverter.convertToPdf(html, target, converterProperties);

        /* extract output as bytes */
        byte[] bytes = target.toByteArray();

        /* Send the response as downloadable PDF */
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=order.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(bytes);
    }
}
