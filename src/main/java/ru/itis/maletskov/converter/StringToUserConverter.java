package ru.itis.maletskov.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.itis.maletskov.model.User;
import ru.itis.maletskov.repository.UserRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class StringToUserConverter implements Converter<String, User> {
    private final UserRepository userRepository;

    @Override
    public User convert(String id) {
        Optional<User> candidate = userRepository.findById(Long.parseLong(id));
        return candidate.orElse(null);
    }
}
