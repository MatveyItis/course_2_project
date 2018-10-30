package ru.itis.maletskov.models;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class Album {
    private Integer albumId;
    private Integer artistId;
    private String albumTitle;
    private Integer albumYear;
    private List<Song> albumSongs;
}