package ru.itis.models;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class Library {
    private Long clientId;
    private List<Song> songs;
}
