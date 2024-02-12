package com.example.lab_91;

import com.example.lab_91.Entities.Album;
import com.example.lab_91.Entities.Artist;
import com.example.lab_91.Entities.Genre;
import com.github.javafaker.Faker;

import java.util.*;

/**
 * this class is responsible with the generate fake data for the Artist/Genre/Album
 */
public class GenerateData {


    private final Faker generateData;

    public GenerateData() {
        this.generateData = new Faker();
    }

    /**
     * this method creates fake Artist data
     * @param quantity
     * @return
     */
    public List<Artist> createArtist(long quantity) {
        List<Artist> artists = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            Artist artist = new Artist();
            artist.setName((generateData.artist().name()));
            artists.add(artist);
        }
        return artists;
    }

    /**
     * this method creates fake Genres data
     * @param quantity
     * @return
     */
    public List<Genre> createGenres(long quantity) {
        List<Genre> genres = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            Genre genre = new Genre();
            genre.setName((generateData.music().genre()));
            genres.add(genre);
        }
        return genres;
    }

    /**
     * this method creates fake Albums data
     * @param artists
     * @param genres
     * @param quantity
     * @return
     */
    public List<Album> createAlbums(List<Artist> artists, List<Genre> genres, long quantity) {
        List<Album> albums = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            Album album = new Album();
            StringBuilder albumName = new StringBuilder();
            albumName.append("Album nr. ");
            albumName.append(i);
            album.setTitle(albumName.toString());
            album.setReleaseYear(generateData.number().numberBetween(1950, 2023));
            Random random = new Random();
            int nr = random.nextInt(artists.size()-1);
            album.setArtist(artists.get(nr));
            Set<Genre> albumGenres = new HashSet<>();
            for (int j = 0; j < generateData.number().numberBetween(1, 6); j++) {
                albumGenres.add(genres.get(generateData.number().numberBetween(0, genres.size() - 1)));
            }
            album.setGenres(albumGenres);
            albums.add(album);
        }
        return albums;
    }
}
