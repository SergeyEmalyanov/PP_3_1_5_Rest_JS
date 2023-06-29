package PP_3_1_2_Boot_Security.util;

import PP_3_1_2_Boot_Security.dao.UserDao;
import PP_3_1_2_Boot_Security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    private final UserDao userDao;

    @Autowired
    public UserValidator(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        if (userDao.findByName(user.getName()).isPresent()){
            errors.rejectValue("name","","Пользователь с таким именем уже существует");
        }
    }
}
