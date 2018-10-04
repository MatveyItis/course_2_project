package ru.kpfu.itis.maletskov.services;

import ru.kpfu.itis.maletskov.forms.LoginForm;
import ru.kpfu.itis.maletskov.forms.UserForm;

public interface UsersService {
    void signUp(UserForm userForm);
    void signIn(LoginForm loginForm);
}
