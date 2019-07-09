package ru.itis.maletskov.util.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.itis.maletskov.form.UserForm;
import ru.itis.maletskov.repository.UserRepository;

import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class UserFormValidator implements Validator {
    private final UserRepository userRepository;
    private Pattern pattern = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+");


    @Override
    public boolean supports(Class<?> aClass) {
        return UserForm.class.equals(aClass);
    }

    @Override
    public void validate(Object userForm, Errors errors) {
        UserForm form = (UserForm) userForm;
        if (form.getFirstName().isEmpty()) {
            errors.rejectValue("firstName", "error.field.empty");
        }
        if (form.getLastName().isEmpty()) {
            errors.rejectValue("lastName", "error.field.empty");
        }
        if (!form.getEmail().equals(form.getEmailOld())) {
            if (!checkEmail(form.getEmail()) || checkDuplicateEmail(form.getEmail())) {
                errors.rejectValue("email", "userform.email");
            }
        }
        if (!form.getUsername().equals(form.getUsernameOld())) {
            if (checkDuplicateUsername(form.getUsername())) {
                errors.rejectValue("username", "userform.username");
            }
        }
        if (form.getNewPassword() != null && form.getNewPassword2() != null) {
            if (!form.getNewPassword().isEmpty() && form.getNewPassword2().isEmpty()) {
                errors.rejectValue("newPassword2", "error.field.empty");
            }
            if (form.getNewPassword().isEmpty() && !form.getNewPassword2().isEmpty()) {
                errors.rejectValue("newPassword", "error.field.empty");
            }
            if (!form.getNewPassword().isEmpty() && !form.getNewPassword2().isEmpty()) {
                if (!form.getNewPassword2().equals(form.getNewPassword())) {
                    errors.rejectValue("newPassword", "userform.password.incorrect");
                    errors.rejectValue("newPassword2", "userform.password.incorrect");
                }
            }
        }
    }

    private boolean checkDuplicateUsername(String username) {
        return userRepository.existsUserByUsername(username);
    }

    private boolean checkEmail(String email) {
        return pattern.matcher(email).lookingAt();
    }

    private boolean checkDuplicateEmail(String email) {
        return userRepository.existsUserByEmail(email);
    }
}
