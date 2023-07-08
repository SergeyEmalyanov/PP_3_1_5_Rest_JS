package PP_3_1_2_Boot_Security.util;

import PP_3_1_2_Boot_Security.model.User;
import PP_3_1_2_Boot_Security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    private final UserRepository userRepository;

    @Autowired
    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        if (userRepository.findByName(user.getName()).isPresent()){
            errors.rejectValue("name","","Пользователь с таким именем уже существует");
        }
        if (user.getPassword()==null || user.getPassword().equals("")){
            errors.rejectValue("password","","Пароль не должен быть пустым");
        }
    }
}
