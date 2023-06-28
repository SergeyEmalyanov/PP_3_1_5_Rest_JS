package PP_3_1_2_Boot_Security.services;

import PP_3_1_2_Boot_Security.dao.UserDao;
import PP_3_1_2_Boot_Security.model.User;
import PP_3_1_2_Boot_Security.secure.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserDao userDao;

    @Autowired
    public UserDetailServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userDao.findByName(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("UserNotFound!");
        } else {
            return new UserDetailsImpl(user.get());
        }
    }
}
