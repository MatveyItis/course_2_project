package ru.itis.maletskov.services;

import ru.itis.maletskov.forms.ArtistForm;

public interface ArtistService {
    void addArtist(ArtistForm artistForm);
    void getArtist(ArtistForm artistForm);
}
