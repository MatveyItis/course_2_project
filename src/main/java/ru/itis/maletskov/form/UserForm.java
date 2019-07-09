package ru.itis.maletskov.form;

import lombok.Getter;
import lombok.Setter;
import ru.itis.maletskov.model.User;

@Getter
@Setter
public class UserForm {
    private String username;
    private String usernameOld;
    private String firstName;
    private String lastName;
    private String aboutMe;
    private String newPassword;
    private String newPassword2;
    private String email;
    private String emailOld;

    public static UserForm fromUserToForm(User user) {
        UserForm form = new UserForm();
        form.setUsername(user.getUsername());
        form.setUsernameOld(user.getUsername());
        form.setFirstName(user.getFirstName());
        form.setLastName(user.getLastName());
        form.setAboutMe(user.getAboutMe());
        form.setEmail(user.getEmail());
        form.setEmailOld(user.getEmail());
        return form;
    }
}
