package ru.itis.maletskov.models;

import lombok.*;

import java.util.List;

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
    private List<Song> songs;
}
