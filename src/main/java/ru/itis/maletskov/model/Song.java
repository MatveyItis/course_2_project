package ru.itis.maletskov.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "songs")
@EqualsAndHashCode(exclude = {"author"})
public class Song implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "tag")
    private String tag;

    @Column(name = "audio_filename")
    private String audioFileName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "song_img", referencedColumnName = "id")
    private Img songImg;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    @OneToOne
    @JoinColumn(name = "album_id")
    private Album album;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "song_likes",
            joinColumns = {@JoinColumn(name = "song_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private Set<User> likes = new HashSet<>();
}