package com.example.lab_91.Entities;

/**
 * @author Pal Alexandra
 * This class describes the entity for table genres9 from the database.
 */

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * this class is the representation of Genres database in OOP
 */

@Data
@NoArgsConstructor
@Entity
@Table(name = "Genres")
public class Genre {
    @Id
    @GeneratedValue
//    @Column(name = "id_genre")
    private Integer id;
    //    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "genreSet", fetch = FetchType.EAGER)
    private Set<Album> albumSet;
    public Genre(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return Objects.equals(id, genre.id) && Objects.equals(name, genre.name) && Objects.equals(albumSet, genre.albumSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
