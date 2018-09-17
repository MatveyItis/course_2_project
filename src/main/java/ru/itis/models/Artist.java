package ru.itis.models;

import lombok.*;
import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class Artist {
    private int artistId;
    private String lastName;
    private String firstName;
    private Date birthday;
    private int genreId;
}
