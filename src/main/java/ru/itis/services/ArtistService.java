package ru.itis.services;

import ru.itis.forms.ArtistForm;

public interface ArtistService {
    void addArtist(ArtistForm artistForm);
    void getArtist(ArtistForm artistForm);
}
