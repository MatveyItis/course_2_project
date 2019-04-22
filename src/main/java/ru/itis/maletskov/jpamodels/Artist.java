package ru.itis.maletskov.jpamodels;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@Table(name = "artists")
public class Artist implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "nickname")
    private String nickname;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "main_img", referencedColumnName = "id")
    private Img image;
    
    @OneToMany
    private Set<Album> albums;
}