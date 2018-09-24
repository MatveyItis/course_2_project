package ru.itis.models;

import lombok.*;

import java.time.LocalDate;

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
}
