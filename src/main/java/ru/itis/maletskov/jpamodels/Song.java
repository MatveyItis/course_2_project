package ru.itis.maletskov.jpamodels;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

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

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", nullable = false)
    private User author;
}