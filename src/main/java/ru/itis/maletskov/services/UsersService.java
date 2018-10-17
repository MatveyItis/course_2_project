package ru.itis.maletskov.services;

import ru.itis.maletskov.forms.UserForm;
import ru.itis.maletskov.forms.LoginForm;

public interface UsersService {
    boolean signUp(UserForm userForm);
    boolean signIn(LoginForm loginForm);
}
