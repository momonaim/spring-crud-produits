package ma.fsts.spring_crud.controllers;

import org.springframework.web.bind.annotation.GetMapping;

public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index";
    }
}
