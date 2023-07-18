package PP_3_1_4_Bootstrap.controllers;

import PP_3_1_4_Bootstrap.model.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/")
    public String showAll(Model model, @AuthenticationPrincipal User user
            , @ModelAttribute("newUser") User newUser) {
        model.addAttribute("user", user);
        return "admin";
    }
}
