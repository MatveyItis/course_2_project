package ru.itis.maletskov.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "img")
public class Img {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "filename")
    private String fileName;
}
