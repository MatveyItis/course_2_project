package ru.itis.maletskov.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.maletskov.forms.AuthForm;
import ru.itis.maletskov.forms.LoginForm;
import ru.itis.maletskov.forms.UserForm;
import ru.itis.maletskov.models.Auth;
import ru.itis.maletskov.models.User;
import ru.itis.maletskov.repositories.AuthRepository;
import ru.itis.maletskov.repositories.UsersRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersServiceImpl implements UsersService {
    private UsersRepository usersRepository;
    private AuthRepository authRepository;

    public UsersServiceImpl() {
    }

    public UsersServiceImpl(UsersRepository usersRepository, AuthRepository authRepository) {
        this.usersRepository = usersRepository;
        this.authRepository = authRepository;
    }

    @Override
    public boolean signUp(UserForm userForm) {
        User user = User.builder()
                .email(userForm.getEmail())
                .firstName(userForm.getFirstName())
                .lastName(userForm.getLastName())
                .build();
        if (!userForm.getPasswordFirst().equals(userForm.getPasswordSecond())) {
            return false;
        }
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setHashPassword(encoder.encode(userForm.getPasswordFirst()));
        if ((user.getFirstName().length() >= 2) &&
                (user.getLastName().length() >= 2) &&
                (userForm.getPasswordFirst().length() >= 6) &&
                (userForm.getPasswordSecond().length() >= 6)) {
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
            PasswordEncoder encoder = new BCryptPasswordEncoder();
            return encoder.matches(loginForm.getPassword(), user.getHashPassword());
        } else {
            return false;
        }
    }

    @Override
    public void logOut(User user) {
        authRepository.delete(user.getClientId());
    }

    @Override
    public void auth(AuthForm authForm) {
        Auth auth = Auth.builder()
                .uuid(authForm.getUuid())
                .clientId(authForm.getClientId())
                .build();
        authRepository.save(auth);
    }

    @Override
    public Integer getUserIdByCookieValue(String cookieValue) {
        Optional<Auth> authOptional = authRepository.findAuthByCookieValue(cookieValue);
        if (authOptional.isPresent()) {
            Auth auth = authOptional.get();
            return auth.getClientId();
        } else {
            return 0;
        }
    }

    @Override
    public User findByEmail(String email) {
        Optional<User> optionalUser = usersRepository.findOneByEmail(email);
        return optionalUser.orElse(null);
    }

    @Override
    public User findOneById(Integer id) {
        Optional<User> optionalUser = usersRepository.findOne(id);
        return optionalUser.orElse(null);
    }

    @Override
    public List<User> searchPeople(String userName) {
        if (userName.equals("")) {
            return new ArrayList<>();
        }
        if (!userName.contains(" ")) {
            return usersRepository.searchPeopleByName(userName).orElse(new ArrayList<>());
        }
        String[] name = userName.split(" ");
        if (name.length == 2) {
            return usersRepository.searchPeopleByName(name[0] + name[1]).orElse(new ArrayList<>());
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public boolean updateInfo(UserForm userForm, Integer clientId) {
        if (!userForm.getPasswordFirst().equals(userForm.getPasswordSecond()))
            return false;
        if (userForm.getPasswordFirst().length() < 6 || userForm.getPasswordFirst().length() > 12)
            return false;
        if (userForm.getPasswordSecond().length() < 6 || userForm.getPasswordSecond().length() > 12)
            return false;
        System.out.println("до упд дошел");
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = User.builder()
                .clientId(clientId)
                .firstName(userForm.getFirstName())
                .lastName(userForm.getLastName())
                .email(userForm.getEmail())
                .hashPassword(encoder.encode(userForm.getPasswordFirst()))
                .build();
        usersRepository.update(user);
        return true;
    }
}
