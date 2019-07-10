package ru.itis.maletskov.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.maletskov.form.UserForm;
import ru.itis.maletskov.model.Role;
import ru.itis.maletskov.model.Song;
import ru.itis.maletskov.model.User;
import ru.itis.maletskov.repository.UserRepository;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean addUser(User user, Boolean isSinger) {
        if (userRepository.existsUserByUsername(user.getUsername()) ||
                userRepository.existsUserByEmail(user.getEmail())) {
            return false;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(isSinger ? Collections.singleton(Role.SINGER) : Collections.singleton(Role.USER));
        userRepository.save(user);
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userCandidate = userRepository.findByUsername(username);
        if (!userCandidate.isPresent()) {
            throw new UsernameNotFoundException("User by username: " + username + " is not found");
        }
        return userCandidate.get();
    }

    public void subscribe(User currentUser, User user) {
        user.getSubscribers().add(currentUser);
        userRepository.save(user);
    }

    public void unsubscribe(User currentUser, User user) {
        user.getSubscribers().remove(currentUser);
        userRepository.save(user);
    }

    public void addSongToFavourite(Song song, User user) {
        Set<Song> addedSongs = user.getAddedSongs();
        addedSongs.add(song);
        user.setAddedSongs(addedSongs);
        userRepository.save(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    public User updateInfo(User user, UserForm form) {
        if (!form.getNewPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(form.getNewPassword()));
        }
        user.setFirstName(form.getFirstName());
        user.setLastName(form.getLastName());
        user.setUsername(form.getUsername());
        user.setEmail(form.getEmail());
        user.setAboutMe(form.getAboutMe());
        return userRepository.save(user);
    }
}
