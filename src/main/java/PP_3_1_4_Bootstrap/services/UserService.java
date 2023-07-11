package PP_3_1_4_Bootstrap.services;


import PP_3_1_4_Bootstrap.model.Role;
import PP_3_1_4_Bootstrap.model.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserService {
    List<User> getAll();

    void add(User user, Set <Role> roles);

    User getUser(int id);

    void update(User user, String newPass);

    void delete(int id);

    Optional<User> findByName(String name);

}
