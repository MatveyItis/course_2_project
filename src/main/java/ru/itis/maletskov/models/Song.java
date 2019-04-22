package ru.itis.maletskov.models;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class Song implements Serializable {
    private Integer id;
    private String title;
    private Integer duration;
    private Artist artist;
    private String songSrc;
}