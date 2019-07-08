package ru.itis.maletskov.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.itis.maletskov.model.Song;
import ru.itis.maletskov.repository.SongRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class StringToSongConverter implements Converter<String, Song> {
    private final SongRepository songRepository;

    @Override
    public Song convert(String id) {
        try {
            Optional<Song> songOptional = songRepository.findById(Long.parseLong(id));
            return songOptional.orElse(null);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
