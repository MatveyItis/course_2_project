package ru.itis.maletskov.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Album {
    private Integer albumId;
    private Artist artist;
    private String albumTitle;
    private Integer albumYear;
    private String albumCoverSrc;
    private List<Song> albumSongs;
}