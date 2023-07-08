package PP_3_1_2_Boot_Security.services;


import PP_3_1_2_Boot_Security.model.Role;
import PP_3_1_2_Boot_Security.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    List<User> getAll();

    void add(User user, Set<Role> roles);

    User getUser(int id);

    void update(User user, String newPass);

    void delete(int id);
}
