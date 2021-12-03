package us.john.hanna.cps530assignment.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;
import springfox.documentation.annotations.ApiIgnore;

// Ignore this controller for the API docs
@ApiIgnore
@Controller
public class HomeController {

    // This method is mapped to the root url of our app since we didn't pass any arguments to the GetMapping annotation
    @GetMapping
    public RedirectView getHomePage(){

        return new RedirectView("/views/signup");

    }

}
