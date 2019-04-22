package ru.itis.maletskov.services;

import ru.itis.maletskov.models.User;
import ru.itis.maletskov.models.forms.LoginForm;
import ru.itis.maletskov.models.forms.UserForm;

import java.util.List;

public interface UsersService {
    boolean signUp(UserForm userForm);

    boolean signIn(LoginForm loginForm);

    User findByEmail(String email);

    User findOneById(Integer id);

    List<User> searchPeople(String userName);

    boolean updateInfo(UserForm userForm, Integer clientId);
}
