package ru.itis.maletskov.jpamodels;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

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
    @NotBlank(message = "Title cannot be empty")
    private String title;

    @Column(name = "tag")
    @NotBlank(message = "Tag cannot be empty")
    private String tag;

    @Column(name = "audio_filename")
    private String audioFileName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "song_img", referencedColumnName = "id")
    private Img songImg;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    @ManyToMany
    @JoinTable(name = "song_likes",
            joinColumns = {@JoinColumn(name = "song_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private Set<User> likes = new HashSet<>();
}