package br.com.igorma.aiextraction.controller;

import br.com.igorma.aiextraction.model.IntentType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Value("${my-domain}")
    private String myDomain;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("themes", IntentType.getTypes());
        model.addAttribute("domain", myDomain);
        return "index";
    }
}