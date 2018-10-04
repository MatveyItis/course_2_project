package ru.kpfu.itis.maletskov.models;

import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
@Builder
@Setter
public class Artist {
    private Long artistId;
    private String lastName;
    private String firstName;
    private LocalDate birthday;
    private Long genreId;
    private List<Album> albums;
}