package br.com.igorma.aiextraction.controller;

import br.com.igorma.aiextraction.domain.ThemeExtractionProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Value("${my-domain}")
    private String myDomain;

    private final ThemeExtractionProcessor themeExtractionProcessor;


    public HomeController(ThemeExtractionProcessor themeExtractionProcessor) {
        this.themeExtractionProcessor = themeExtractionProcessor;
    }

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("themes", themeExtractionProcessor.getThemesExtraction());
        model.addAttribute("domain", myDomain);
        return "index";
    }
}