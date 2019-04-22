package ru.itis.maletskov.jpamodels;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "album")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "album_date")
    private Date year;

    @ManyToOne
    private User owner;

    @ManyToMany
    @JoinTable(
            name = "album_song",
            joinColumns = @JoinColumn(name = "song_id"),
            inverseJoinColumns = @JoinColumn(name = "album_id")
    )
    private Set<Song> songs;
}