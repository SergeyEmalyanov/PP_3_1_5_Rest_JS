package PP_3_1_2_Boot_Security.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AuthController {

    @GetMapping("/login")
    public String authenticate() {
        return "login";
    }
}
