package PP_3_1_4_Bootstrap.services;

import PP_3_1_4_Bootstrap.model.Role;
import PP_3_1_4_Bootstrap.model.User;
import PP_3_1_4_Bootstrap.repository.RoleRepository;
import PP_3_1_4_Bootstrap.repository.UserRepository;
import PP_3_1_4_Bootstrap.util.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(int id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public Optional<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    @Transactional
    public void add(User user) {
        Set<Role> roles = user.getRoles();
        if (roles.isEmpty()) {
            roles.add(roleRepository.findByName("ROLE_USER").orElse(Role.getRoleUser()));
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(roles);
        for (Role role : roles) {
            role.addUser(user);
        }
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void delete(int id) {
        userRepository.deleteById(id);
    }
}
