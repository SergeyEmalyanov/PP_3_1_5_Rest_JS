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
        Role roleUser = Role.getRoleUser();
        roleRepository.save(roleUser);
        userRoles.add(roleUser);
        User user = new User("user", 30, passwordEncoder.encode("user"), userRoles);
        roleUser.addUser(user);
        userRepository.save(user);
        Set<Role> adminRoles = new HashSet<>();
        Role roleAdmin = Role.getRoleAdmin();
        roleRepository.save(roleAdmin);
        adminRoles.add(roleUser);
        adminRoles.add(roleAdmin);
        User admin = new User("admin", 30, passwordEncoder.encode("admin"), adminRoles);
        roleUser.addUser(admin);
        roleAdmin.addUser(admin);
        userRepository.save(admin);
    }
}