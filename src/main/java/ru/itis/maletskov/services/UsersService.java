package ru.itis.maletskov.services;

import ru.itis.maletskov.forms.UserForm;
import ru.itis.maletskov.forms.LoginForm;
import ru.itis.maletskov.repositories.UsersRepository;

public interface UsersService {
    boolean signUp(UserForm userForm);
    boolean signIn(LoginForm loginForm);
    public UsersRepository getUsersRepository();
}
