package ru.itis.maletskov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.maletskov.jparepositories.UserRepository;
import ru.itis.maletskov.models.Role;
import ru.itis.maletskov.models.Song;
import ru.itis.maletskov.models.User;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public boolean addUser(User user) {
        User userFromDb = userRepository.findByUsername(user.getUsername()).orElse(null);
        if (userFromDb != null) {
            return false;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singleton(Role.USER));
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
}
