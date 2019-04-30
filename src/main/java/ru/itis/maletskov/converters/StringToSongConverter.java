package ru.itis.maletskov.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.itis.maletskov.jpamodels.Song;
import ru.itis.maletskov.jparepositories.SongRepository;

import java.util.Optional;

@Component
public class StringToSongConverter implements Converter<String, Song> {
    private final SongRepository songRepository;

    @Autowired
    public StringToSongConverter(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

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
