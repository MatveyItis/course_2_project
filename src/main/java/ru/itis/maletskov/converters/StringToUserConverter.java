package ru.itis.maletskov.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.itis.maletskov.jpamodels.User;
import ru.itis.maletskov.jparepositories.UserRepository;

import java.util.Optional;

@Component
public class StringToUserConverter implements Converter<String, User> {
    private final UserRepository userRepository;

    @Autowired
    public StringToUserConverter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User convert(String id) {
        Optional<User> candidate = userRepository.findById(Long.parseLong(id));
        return candidate.orElse(null);
    }
}
