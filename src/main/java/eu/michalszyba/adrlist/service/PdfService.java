package eu.michalszyba.adrlist.service;

import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Service
public class PdfService {

    private static final String PDF_RESOURCES = "/pdf-resources/";
    private final CompanyService companyService;
    private final SpringTemplateEngine templateEngine;

    @Autowired
    public PdfService(CompanyService companyService, SpringTemplateEngine templateEngine) {
        this.companyService = companyService;
        this.templateEngine = templateEngine;
    }

    private Context getContext() {
        Context context = new Context();
        context.setVariable("companies", companyService.getAllCompany());
        return context;
    }

    private String loadAndFillTemplate(Context context) {
        return templateEngine.process("/company/list-company-pdf", context);
    }

    private File renderPdf(String html) throws IOException, DocumentException {
        File file = File.createTempFile("students", ".pdf");
        OutputStream outputStream = new FileOutputStream(file);
        ITextRenderer renderer = new ITextRenderer(20f * 4f / 3f, 20);
        renderer.setDocumentFromString(html, new ClassPathResource(PDF_RESOURCES).getURL().toExternalForm());
        renderer.layout();
        renderer.createPDF(outputStream);
        outputStream.close();
        file.deleteOnExit();
        return file;
    }

    public File generatePdf() throws DocumentException, IOException {
        Context context = getContext();
        String html = loadAndFillTemplate(context);

        return renderPdf(html);
    }
}
