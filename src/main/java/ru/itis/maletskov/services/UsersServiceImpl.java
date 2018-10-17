package ru.itis.maletskov.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.maletskov.forms.UserForm;
import ru.itis.maletskov.forms.LoginForm;
import ru.itis.maletskov.models.User;
import ru.itis.maletskov.repositories.UsersRepository;

import java.util.Optional;

public class UsersServiceImpl implements UsersService {
    private UsersRepository usersRepository;
    private PasswordEncoder encoder;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
        this.encoder = new BCryptPasswordEncoder();
    }

    @Override
    public boolean signUp(UserForm userForm) {
        User user = User.builder()
                .email(userForm.getEmail())
                .hashPassword(encoder.encode(userForm.getPassword()))
                .firstName(userForm.getFirstName())
                .lastName(userForm.getLastName())
                .build();
        if ((user.getFirstName().length() >= 2) &&
                (user.getLastName().length() >= 2) &&
                (userForm.getPassword().length() >= 6)) {
            usersRepository.save(user);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean signIn(LoginForm loginForm) {
        Optional<User> userOptional = usersRepository.findOneByEmail(loginForm.getEmail());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (!encoder.matches(loginForm.getPassword(), user.getHashPassword())) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
}
