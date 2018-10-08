package ru.itis.models;

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
    private String nickname;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String genreName;
    private List<Album> albums;
}