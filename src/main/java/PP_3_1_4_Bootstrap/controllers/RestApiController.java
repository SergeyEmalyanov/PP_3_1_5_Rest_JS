package PP_3_1_4_Bootstrap.controllers;

import PP_3_1_4_Bootstrap.model.User;
import PP_3_1_4_Bootstrap.services.UserService;
import PP_3_1_4_Bootstrap.util.UserErrorResponse;
import PP_3_1_4_Bootstrap.util.UserNotAddException;
import PP_3_1_4_Bootstrap.util.UserNotFoundException;
import PP_3_1_4_Bootstrap.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api")
public class RestApiController {
    private final UserService userService;
    private final UserValidator userValidator;

    @Autowired
    public RestApiController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }


    @GetMapping("/admin")
    public ResponseEntity<List<User>> showAll() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @GetMapping("admin/{id}")
    public ResponseEntity<User> showUser(@PathVariable("id") int id) {
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

    @PostMapping("/admin")
    public ResponseEntity<User> save(@RequestBody @Valid User user, BindingResult bindingResult) {
        System.err.println("из контроллера " + user);
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage());
            }
            throw new UserNotAddException(errorMsg.toString());
        }
        userService.add(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/admin/{id}")
    public ResponseEntity<User> update(@RequestBody User user) {
        userService.add(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<User> delete(@PathVariable("id") int id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(UserNotFoundException e) {
        UserErrorResponse userErrorResponse = new UserErrorResponse("User not found", System.currentTimeMillis());
        return new ResponseEntity<>(userErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(UserNotAddException e) {
        UserErrorResponse userErrorResponse = new UserErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(userErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
