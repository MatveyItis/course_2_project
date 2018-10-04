package ru.itis.services;

import ru.itis.forms.UserForm;
import ru.itis.forms.LoginForm;

public interface UsersService {
    void signUp(UserForm userForm);
    void signIn(LoginForm loginForm);
}
