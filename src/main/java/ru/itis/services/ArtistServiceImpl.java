package ru.itis.services;

import ru.itis.forms.ArtistForm;
import ru.itis.models.Artist;
import ru.itis.repositories.ArtistRepositoryConnectionImpl;

public class ArtistServiceImpl implements ArtistService {
    private ArtistRepositoryConnectionImpl artistRepository;

    public ArtistServiceImpl(ArtistRepositoryConnectionImpl artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public void addArtist(ArtistForm artistForm) {
        Artist artist = Artist.builder()
                .nickname(artistForm.getNickname())
                .firstName(artistForm.getFirstName())
                .lastName(artistForm.getLastName())
                .birthday(artistForm.getBirthday())
                .genreId(artistForm.getGenre().getGenreId())
                .build();
        artistRepository.save(artist);
    }

    @Override
    public void getArtist(ArtistForm artistForm) {

    }
}
