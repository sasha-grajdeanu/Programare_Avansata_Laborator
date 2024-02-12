package com.example.lab_91.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * this class is the representation of Artist database in OOP
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "Artists")
public class Artist {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    @OneToMany(mappedBy = "artist", fetch = FetchType.EAGER )
    private List<Album> albumList;

    public Artist(String name) {
        this.name = name;
        albumList = new ArrayList<>();
    }

    public void setAlbumList(List<Album> albumList) {
        this.albumList = albumList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artist artist = (Artist) o;
        return Objects.equals(id, artist.id) && Objects.equals(name, artist.name) && Objects.equals(albumList, artist.albumList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, albumList);
    }

    @Override
    public String toString() {
        return "Artist{" + "id=" + id + ", name='" + name +'}';
    }
}
