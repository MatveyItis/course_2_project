package ru.kpfu.itis.maletskov.models;

import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
public class Genre {
    private Long genreId;
    private String genreName;
}