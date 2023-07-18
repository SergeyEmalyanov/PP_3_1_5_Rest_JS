package PP_3_1_4_Bootstrap.services;


import PP_3_1_4_Bootstrap.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAll();

    void add(User user);

    User getUser(int id);

    void delete(int id);

    Optional<User> findByName(String name);

}
