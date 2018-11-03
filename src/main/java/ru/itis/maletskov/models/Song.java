package ru.itis.maletskov.models;

import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
public class Song {
    private Integer songId;
    private String title;
    private Integer duration;
    private Integer artistId;
    private String songSrc;
}