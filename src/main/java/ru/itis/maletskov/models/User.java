package ru.itis.maletskov.models;

import lombok.*;

@Data
@Builder
public class User {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
