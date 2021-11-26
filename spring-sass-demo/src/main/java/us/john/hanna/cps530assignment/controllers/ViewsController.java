package us.john.hanna.cps530assignment.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import us.john.hanna.cps530assignment.domain.LoginRequest;
import us.john.hanna.cps530assignment.domain.SignupRequest;
import us.john.hanna.cps530assignment.domain.TodoDto;

import java.util.Map;

@Controller
@RequestMapping("views")
@RequiredArgsConstructor
public class ViewsController {

    @Value("${app.origin}")
    private String origin;

    @GetMapping("login")
    public String login(Model model){

        model.addAttribute("origin", origin);
        return "login";

    }

    @GetMapping("signup")
    public String signup(Model model){

        model.addAttribute("origin", origin);
        return "signup";

    }

    @PostMapping("signup")
    public String processSignUpForm(@RequestParam Map<String, String> body){

        SignupRequest signupRequest = new SignupRequest(body.get("username"), body.get("password"), body.get("confirmPassword"));
        return "error";

    }

    @PostMapping(path = "login")
    public String getTodosPage(Model model, @RequestParam Map<String, String> body){

        LoginRequest loginRequest = new LoginRequest(body.get("username"), body.get("password"));
        RestTemplate rest = new RestTemplate();
        ResponseEntity<String> response = rest.postForEntity(origin + "/api/auth/login", new HttpEntity<>(loginRequest), String.class);
        String token = response.getBody();
        if(response.getStatusCode().is2xxSuccessful()){

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);
            ResponseEntity<TodoDto[]> todosResponse = rest.exchange(origin + "/api/todo/allTodos",
                    HttpMethod.GET, new HttpEntity(headers), TodoDto[].class);

            if(todosResponse.getStatusCode().is2xxSuccessful()){

                model.addAttribute("todos", todosResponse.getBody());
                model.addAttribute("token", token);
                return "todos-page";

            }else{

                model.addAttribute("error", "Error fetching your todos");
                return "error";

            }

        }else{

            model.addAttribute("error", "Incorrect username or password. Please try again.");
            return "error";

        }

    }

}
