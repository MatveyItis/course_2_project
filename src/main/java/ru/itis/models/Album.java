package ru.itis.models;

import lombok.*;

import java.util.List;

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
    private int albumTracks;
    private List<Song> albumSongs;
}
