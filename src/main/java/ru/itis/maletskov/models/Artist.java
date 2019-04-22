package ru.itis.maletskov.models;

import lombok.*;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Artist implements Serializable {
    private Integer id;
    private String nickname;
    private String firstName;
    private String lastName;
    private Date birthday;
    private String artistImgSrc;
    private List<Album> albums;
}