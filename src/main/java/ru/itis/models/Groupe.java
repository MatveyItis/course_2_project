package ru.itis.models;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode
public class Groupe {
    private int artistId;
    private String groupName;
}
