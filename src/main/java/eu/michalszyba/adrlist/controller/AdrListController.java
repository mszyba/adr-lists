package eu.michalszyba.adrlist.controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.michalszyba.adrlist.model.AdrList;
import eu.michalszyba.adrlist.service.AdrListService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
@RequestMapping("/adr")
public class AdrListController {

    private final AdrListService adrListService;

    public AdrListController(AdrListService adrListService) {
        this.adrListService = adrListService;
    }

    @GetMapping("/list")
    public String listAdr(Model model) throws IOException {
        List<AdrList> allAdrList = adrListService.getAllAdrList();


        model.addAttribute("adrLists", allAdrList);
        return "/adr-list/list-adr-all";
    }
}
