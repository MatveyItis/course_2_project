package ru.itis.maletskov.services;

import ru.itis.maletskov.forms.AuthForm;
import ru.itis.maletskov.forms.LoginForm;
import ru.itis.maletskov.forms.UserForm;
import ru.itis.maletskov.models.User;

import java.util.List;

public interface UsersService {
    boolean signUp(UserForm userForm);

    boolean signIn(LoginForm loginForm);

    void logOut(User user);

    void auth(AuthForm authForm);

    Integer getUserIdByCookieValue(String cookieValue);

    User findByEmail(String email);

    User findOneById(Integer id);

    List<User> searchPeople(String userName);

    boolean updateInfo(UserForm userForm, Integer clientId);
}
