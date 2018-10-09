package ru.itis.models;

import lombok.*;

import java.sql.Date;
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
    private Date birthday;
    private String genreName;
    private List<Album> albums;
}