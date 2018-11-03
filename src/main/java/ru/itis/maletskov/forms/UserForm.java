package ru.itis.maletskov.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserForm {
    private String email;
    private String passwordFirst;
    private String passwordSecond;
    private String firstName;
    private String lastName;
}
