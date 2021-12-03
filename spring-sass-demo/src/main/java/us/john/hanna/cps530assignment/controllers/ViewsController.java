package us.john.hanna.cps530assignment.controllers;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.annotations.ApiIgnore;
import us.john.hanna.cps530assignment.domain.LoginRequest;
import us.john.hanna.cps530assignment.domain.SignupRequest;
import us.john.hanna.cps530assignment.domain.TodoDto;

import java.util.Map;

@ApiIgnore
@Controller
@RequestMapping("views")
public class ViewsController {

    // Inject the value of app.origin from our application-dev.properties. This should be set to http://localhost:8080.
    @Value("${app.origin}")
    private String origin;

    @GetMapping("login")
    // The Model class is for adding data to the page we will direct the user to.
    public String login(Model model){

        // Send the base URL of our app to the page. This data will be accessed via an id of "origin".
        model.addAttribute("origin", origin);
        // Return any HTML page in the folder src/main/resources/templates named login.html
        return "login";

    }

    @GetMapping("signup")
    public String signup(Model model){

        model.addAttribute("origin", origin);
        return "signup";

    }

    @PostMapping("signup")
    // @RequestParam Map<String, String> body -> the key-value pairs corresponding to form data
    public String processSignUpForm(Model model, @RequestParam Map<String, String> body){

        // SignupRequest is just a POJO with the username, password, and confirm password
        SignupRequest signupRequest = new SignupRequest(body.get("username"), body.get("password"), body.get("confirmPassword"));
        RestTemplate rest = new RestTemplate();

        try {
            ResponseEntity<String> response = rest.postForEntity(origin + "/api/auth/signup",
                    new HttpEntity<>(signupRequest), String.class);
            model.addAttribute("origin", origin);
            return "successful-signup";
        }catch (HttpClientErrorException ex){
            model.addAttribute("error", ex.getLocalizedMessage());
            return "error";
        }

    }

    @PostMapping(path = "login")
    // @RequestParam Map<String, String> body -> the key value pairs corresponding to form data
    public String getTodosPage(Model model, @RequestParam Map<String, String> body){

        try{

            // LoginRequest is a class we created to represent a request body for the login endpoint of our API. It is just a POJO.
            LoginRequest loginRequest = new LoginRequest(body.get("username"), body.get("password"));
            // The org.springframework.web.client.RestTemplate class is for sending HTTP requests.
            RestTemplate rest = new RestTemplate();
            // Send a post request to our API to login using the loginRequest object as the request body
            ResponseEntity<String> response = rest.postForEntity(origin + "/api/auth/login", new HttpEntity<>(loginRequest), String.class);

            try{

                // Retrieve the security token (if successful) from the response
                String token = response.getBody();
                // Add the security token to the Authorization header
                HttpHeaders headers = new HttpHeaders();
                headers.set("Authorization", "Bearer " + token);
                // Send an HTTP request to get all of this user's todos
                ResponseEntity<TodoDto[]> todosResponse = rest.exchange(origin + "/api/todo/allTodos",
                        HttpMethod.GET, new HttpEntity(headers), TodoDto[].class);
                // Add the todos from this user on the todos page
                model.addAttribute("todos", todosResponse.getBody());
                // Send the security token to the todos-page (just in case)
                model.addAttribute("token", token);
                return "todos-page";

            }catch(HttpClientErrorException ex){

                model.addAttribute("error", "Error fetching your todos");
                return "error";

            }

        }catch(HttpClientErrorException ex){

            model.addAttribute("error", "Incorrect username or password. Please try again.");
            return "error";

        }

    }

}
