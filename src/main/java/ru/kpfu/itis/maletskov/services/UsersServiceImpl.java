package ru.kpfu.itis.maletskov.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kpfu.itis.maletskov.forms.LoginForm;
import ru.kpfu.itis.maletskov.forms.UserForm;
import ru.kpfu.itis.maletskov.models.User;
import ru.kpfu.itis.maletskov.repositories.UsersRepository;
import java.util.Optional;

public class UsersServiceImpl implements UsersService {
    private UsersRepository usersRepository;
    private PasswordEncoder encoder;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
        this.encoder = new BCryptPasswordEncoder();
    }

    @Override
    public void signUp(UserForm userForm) {
        User user = User.builder()
                .email(userForm.getEmail())
                .hashPassword(encoder.encode(userForm.getPassword()))
                .firstName(userForm.getFirstName())
                .lastName(userForm.getLastName())
                .build();
        usersRepository.save(user);
    }

    @Override
    public void signIn(LoginForm loginForm) {
        Optional<User> userOptional = usersRepository.findOneByEmail(loginForm.getEmail());
    }
}
