package com.esprit.tic.twin.springproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class Tache implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long idTache; //clé primaire
    private LocalDate dateTache;
    private int duree;
    private float tarifHoraire;
    @Enumerated(EnumType.STRING) //This line is used to when I add to the DB, it adds the value string instead of 0 & 1 ...
    private TypeTache typeTache;
    @ManyToOne
    @JsonIgnore
    Etudiant etudiantOrdinaire;
    @OneToOne(mappedBy = "tache")
    @JsonIgnore
    private Etudiant etudiantResponsable;
}
