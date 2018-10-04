package ru.kpfu.itis.maletskov.models;

import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
public class Song {
    private Long songId;
    private String title;
    private Long duration;
    private Long artistId;
}