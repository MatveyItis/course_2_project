package ru.itis.models;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class Album {
    private int albumId;
    private int artistId;
    private String albumTitle;
    private int albumYear;
    private int album_tracks;
}
