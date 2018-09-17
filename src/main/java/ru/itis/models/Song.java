package ru.itis.models;

import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
public class Song {
    private int songId;
    private String title;
    private int duration;
    private int artistId;
}
