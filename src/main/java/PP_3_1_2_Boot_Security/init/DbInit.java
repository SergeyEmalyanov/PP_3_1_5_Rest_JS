package PP_3_1_2_Boot_Security.init;

import PP_3_1_2_Boot_Security.model.Role;
import PP_3_1_2_Boot_Security.model.User;
import PP_3_1_2_Boot_Security.repository.RoleRepository;
import PP_3_1_2_Boot_Security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class DbInit {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DbInit(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    private void postConstruct() {
        Set<Role> userRoles = new HashSet<>();
        Role roleUser = getRoleUser();
        userRoles.add(roleUser);
        User user = new User("user", 30, passwordEncoder.encode("user"), userRoles);
        roleUser.addUser(user);
        userRepository.save(user);
        Set<Role> adminRoles = new HashSet<>();
        Role roleAdmin = getRoleAdmin();
        adminRoles.add(roleUser);
        adminRoles.add(roleAdmin);
        User admin = new User("admin", 30, passwordEncoder.encode("admin"), adminRoles);
        roleUser.addUser(admin);
        roleAdmin.addUser(admin);
        userRepository.save(admin);
    }

    private Role getRoleUser() {
        Role roleUser;
        Optional<Role> opRoleUser = roleRepository.findByName("ROLE_USER");
        if (opRoleUser.isEmpty()) {
            roleUser = Role.getRoleUser();
            roleRepository.save(roleUser);
        } else {
            roleUser = opRoleUser.get();
        }
        return roleUser;
    }

    private Role getRoleAdmin() {
        Role roleAdmin;
        Optional<Role> opRoleAdmin = roleRepository.findByName("ROLE_ADMIN");
        if (opRoleAdmin.isEmpty()) {
            roleAdmin = Role.getRoleAdmin();
            roleRepository.save(roleAdmin);
        } else {
            roleAdmin = opRoleAdmin.get();
        }
        return roleAdmin;
    }
}