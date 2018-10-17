package ru.itis.maletskov.models;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class Album {
    private Long albumId;
    private Long artistId;
    private String albumTitle;
    private Long albumYear;
    private Long albumTracks;
    private List<Song> albumSongs;
}