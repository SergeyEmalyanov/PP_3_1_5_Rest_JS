package PP_3_1_4_Bootstrap.controllers;

import PP_3_1_4_Bootstrap.model.Role;
import PP_3_1_4_Bootstrap.model.User;
import PP_3_1_4_Bootstrap.services.RoleService;
import PP_3_1_4_Bootstrap.services.UserService;
import PP_3_1_4_Bootstrap.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;
    private final UserValidator userValidator;

    @Autowired
    public AdminController(UserService userService, RoleService roleService, UserValidator userValidator) {
        this.userService = userService;
        this.roleService = roleService;
        this.userValidator = userValidator;
    }

    @GetMapping("/")
    public String showAll(Model model, @AuthenticationPrincipal User user
            , @ModelAttribute("newUser") User newUser) {
        model.addAttribute("user", user);
        model.addAttribute("users", userService.getAll());
        model.addAttribute("rolesList", roleService.getAllRoles());
        return "admin";
    }

    @PostMapping("/")
    public String save(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "redirect:/admin/";
        }
        Set<Role> roles = user.getRoles();
        roles.add(roleService.findByName("ROLE_USER").orElse(Role.getRoleUser()));
        userService.add(user, roles);
        return "redirect:/admin/";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user
            , @RequestParam(value = "new_pass") String newPass) {
        Set<Role> roles = user.getRoles();
        for (Role role : roles) {
            role.addUser(user);
        }
        userService.update(user, newPass);
        return "redirect:/admin/";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/admin/";
    }
}
