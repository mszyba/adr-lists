package eu.michalszyba.adrlist.controller;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import eu.michalszyba.adrlist.model.Company;
import eu.michalszyba.adrlist.model.Delivery;
import eu.michalszyba.adrlist.service.CompanyService;
import eu.michalszyba.adrlist.service.DeliveryService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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

    private final ServletContext servletContext;
    private final TemplateEngine templateEngine;
    private final CompanyService companyService;
    private final DeliveryService deliveryService;

    public PdfController(ServletContext servletContext, TemplateEngine templateEngine, CompanyService companyService, DeliveryService deliveryService) {
        this.servletContext = servletContext;
        this.templateEngine = templateEngine;
        this.companyService = companyService;
        this.deliveryService = deliveryService;
    }

    @RequestMapping(path = "/pdf")
    public ResponseEntity<?> getPDF(HttpServletRequest request, HttpServletResponse response) {

        /* Do Business Logic*/

//        Order order = OrderHelper.getOrder();
        List<Company> companies = companyService.getAllCompany();

        Delivery oneDelivery = deliveryService.getOneDelivery(1L);


        /* Create HTML using Thymeleaf template Engine */
        WebContext context = new WebContext(request, response, servletContext);
        context.setVariable("companies", companies);
        context.setVariable("delivery", oneDelivery);
        String orderHtml = templateEngine.process("/pdf/order-pdf", context);

        /* Setup Source and target I/O streams */
        ByteArrayOutputStream target = new ByteArrayOutputStream();
        ConverterProperties converterProperties = new ConverterProperties();
        converterProperties.setBaseUri("http://localhost:8080");

        /* Call convert method */
        HtmlConverter.convertToPdf(orderHtml, target, converterProperties);

        /* extract output as bytes */
        byte[] bytes = target.toByteArray();

        /* Send the response as downloadable PDF */
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=order.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(bytes);
    }
}
