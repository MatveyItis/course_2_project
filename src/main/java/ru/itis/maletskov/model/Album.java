package ru.itis.maletskov.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
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
    private Date date;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;

    @OneToOne
    @JoinColumn(name = "album_cover", referencedColumnName = "id")
    private Img img;

    @ManyToMany(mappedBy = "album", cascade = CascadeType.ALL)
    private Set<Song> songs = new HashSet<>();
}