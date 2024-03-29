package br.com.igorma.aiextraction.controller;

import java.util.List;
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
        //TODO: nao deixar isso fixo aqui
        List<String> themes = List.of("Medidas Corporais", "Alimentação", "Outros", "Carro", "Casa",
            "Financeiro");

        model.addAttribute("themes", themes);
        model.addAttribute("domain", myDomain);
        return "index";
    }
}