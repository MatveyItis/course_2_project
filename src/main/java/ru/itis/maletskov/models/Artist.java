package ru.itis.maletskov.models;

import lombok.*;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Artist {
    private Integer artistId;
    private String nickname;
    private String firstName;
    private String lastName;
    private Date birthday;
    private String artistImgSrc;
    //private List<Album> albums;
}