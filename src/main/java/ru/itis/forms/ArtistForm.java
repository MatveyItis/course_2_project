package ru.itis.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArtistForm {
    private String firstName;
    private String lastName;
    private String nickname;
    private LocalDate birthday;
    private String genreName;
}
