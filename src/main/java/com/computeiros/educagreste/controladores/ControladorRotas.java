package com.computeiros.educagreste.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorRotas {

    @GetMapping("/")
    public String root() {
        return "home";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/index")
    public String index() {
        return "home";
    }

    @GetMapping("/autenticar")
    public String login(Model model) {
        return "autenticar";
    }

    @GetMapping("/usuario")
    public String userIndex() {
        return "home";
    }

}
