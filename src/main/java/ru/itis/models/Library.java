package ru.itis.models;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class Library {
    private int clientId;
    private List<Song> songs;
}
