package ru.itis.maletskov.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Library {
    private Integer libraryId;
    private Integer clientId;
    private List<Song> songs;
}
