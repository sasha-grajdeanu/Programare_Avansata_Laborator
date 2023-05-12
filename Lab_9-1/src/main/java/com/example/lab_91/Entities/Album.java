package com.example.lab_91.Entities;

import com.example.lab_91.Entities.Artist;
import com.example.lab_91.Entities.Genre;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * this class is the representation of Albums database in OOP
 */

@NoArgsConstructor
@Data
@Entity
@Table(name = "Albums")
public class Album {

    @Id
    @GeneratedValue
    private Integer id;

    private int releaseYear;

    private String title;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_artist")
    private Artist artist;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "album_genres", joinColumns = @JoinColumn(name = "id_album"), inverseJoinColumns = @JoinColumn(name = "id_genre"))
    private Set<Genre> genreSet;

    public Album(int releaseYear, String name) {
        this.releaseYear = releaseYear;
        this.title = name;
        this.genreSet = new HashSet<>();
    }

    public void setGenres(Set<Genre> albumGenres) {
        genreSet = albumGenres;
    }

    @Override
    public String toString() {
        return "Album{" + "id=" + id + ", releaseYear=" + releaseYear + ", title='" + title + '\'' + ", artist=" + artist + ", genreSet=" + genreSet + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return releaseYear == album.releaseYear && Objects.equals(id, album.id) && Objects.equals(title, album.title) && Objects.equals(artist, album.artist) && Objects.equals(genreSet, album.genreSet);
    }
}
