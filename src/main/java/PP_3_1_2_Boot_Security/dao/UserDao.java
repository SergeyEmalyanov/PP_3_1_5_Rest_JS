package PP_3_1_2_Boot_Security.dao;
import PP_3_1_2_Boot_Security.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    List<User> getAll();
    void add (User user);
    User getUser(int id);
    void update (int id,User user);
    void delete (int id);
    Optional<User> findByName(String name);
}
