package ru.itis.maletskov.services;

import ru.itis.maletskov.forms.AuthForm;
import ru.itis.maletskov.forms.LoginForm;
import ru.itis.maletskov.forms.UserForm;
import ru.itis.maletskov.models.User;
import ru.itis.maletskov.repositories.UsersRepository;

public interface UsersService {
    boolean signUp(UserForm userForm);
    boolean signIn(LoginForm loginForm);
    void logOut(User user);
    UsersRepository getUsersRepository();
    void auth(AuthForm authForm);
    Integer getUserIdByCookieValue(String cookieValue);
}
