package PP_3_1_2_Boot_Security.controllers;

import PP_3_1_2_Boot_Security.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping()
    public String showUserInfo(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = (User) userDetails;
        model.addAttribute("user",user.getUser());
        return "user_page";
    }
}
