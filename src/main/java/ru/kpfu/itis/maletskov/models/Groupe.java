package ru.kpfu.itis.maletskov.models;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode
public class Groupe {
    private Long artistId;
    private String groupName;
}