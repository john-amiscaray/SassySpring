package us.john.hanna.cps530assignment.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class HomeController {

    @GetMapping
    public RedirectView getHomePage(){

        return new RedirectView("/views/login");

    }

}
