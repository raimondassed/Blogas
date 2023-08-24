package com.raimis.Blogas.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
@Column(name = "title")
    private String title;
@Column(name = "header")
    private String header;

    @OneToMany(mappedBy = "topic", orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();
// komentarai, kur ives vartotojai per frontenda
}



