package ru.itis.maletskov.services;

import ru.itis.maletskov.models.Artist;
import ru.itis.maletskov.repositories.ArtistRepository;

import java.util.List;

public class ArtistServiceImpl implements ArtistService {
    private ArtistRepository artistRepository;

    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public List<Artist> getAllArtists() {
        return artistRepository.findAll().get();
    }
}
