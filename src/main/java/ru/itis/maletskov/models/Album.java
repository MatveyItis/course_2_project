package ru.itis.maletskov.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Album {
    private Integer id;
    private Artist owner;
    private String title;
    private Date year;
    private String albumCoverSrc;
    private List<Song> songs;
}