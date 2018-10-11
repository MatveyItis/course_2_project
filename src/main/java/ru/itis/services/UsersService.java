package ru.itis.services;

import ru.itis.forms.UserForm;
import ru.itis.forms.LoginForm;

public interface UsersService {
    boolean signUp(UserForm userForm);
    boolean signIn(LoginForm loginForm);
}
