package PP_3_1_2_Boot_Security.services;


import PP_3_1_2_Boot_Security.model.User;

import java.util.List;

public interface UserService {
    List<User> getAll();
    void add (User user);
    User getUser(int id);
    void update (int id,User user);
    void delete (int id);

}
