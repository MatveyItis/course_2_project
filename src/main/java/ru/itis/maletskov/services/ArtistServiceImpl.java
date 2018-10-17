package ru.itis.maletskov.services;

import ru.itis.maletskov.forms.ArtistForm;
import ru.itis.maletskov.models.Artist;
import ru.itis.maletskov.repositories.ArtistRepository;

public class ArtistServiceImpl implements ArtistService {
    private ArtistRepository artistRepository;

    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public void addArtist(ArtistForm artistForm) {
        Artist artist = Artist.builder()
                .nickname(artistForm.getNickname())
                .firstName(artistForm.getFirstName())
                .lastName(artistForm.getLastName())
                .birthday(artistForm.getBirthday())
                .genreName(artistForm.getGenreName())
                .build();
        artistRepository.save(artist);
    }

    @Override
    public void getArtist(ArtistForm artistForm) {

    }
}
