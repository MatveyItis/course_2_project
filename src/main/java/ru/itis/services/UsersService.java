package ru.itis.services;

import ru.itis.forms.LoginForm;
import ru.itis.forms.UserForm;

public interface UsersService {
    void signUp(UserForm userForm);
    void signIn(LoginForm loginForm);
}
