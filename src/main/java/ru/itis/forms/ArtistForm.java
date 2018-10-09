package ru.itis.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArtistForm {
    private String firstName;
    private String lastName;
    private String nickname;
    private Date birthday;
    private String genreName;
}
