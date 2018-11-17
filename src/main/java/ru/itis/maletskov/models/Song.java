package ru.itis.maletskov.models;

import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode(exclude = {"isHaving"})
@ToString(exclude = {"isHaving"})
public class Song {
    private Integer songId;
    private String title;
    private Integer duration;
    private Artist artist;
    private String songSrc;
    private boolean isHaving;
}