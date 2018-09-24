package ru.itis.models;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class User {
    private Long clientId;
    private String firstName;
    private String lastName;
    private String email;
    private String hashPassword;
    private Library library;
}
