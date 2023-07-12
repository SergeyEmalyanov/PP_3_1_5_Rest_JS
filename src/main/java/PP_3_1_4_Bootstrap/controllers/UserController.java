package PP_3_1_4_Bootstrap.controllers;

import PP_3_1_4_Bootstrap.model.User;
import PP_3_1_4_Bootstrap.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    private final RoleService roleService;

    @Autowired
    public UserController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping()
    public String showUserInfo(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("user", user);
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "user";
    }
}