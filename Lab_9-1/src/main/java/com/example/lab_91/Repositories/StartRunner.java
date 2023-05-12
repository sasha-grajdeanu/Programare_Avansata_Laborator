package com.example.lab_91.Repositories;

import com.example.lab_91.Entities.Album;
import com.example.lab_91.Entities.Artist;
import com.example.lab_91.Entities.Genre;
import com.example.lab_91.GenerateData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

@Component
public class StartRunner implements ApplicationRunner {

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private GenreRepository genreRepository;

    private int countEntries = 100;

    /**
     * this method will be running by the Spring App
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        GenerateData generateData = new GenerateData();
        List<Artist> artistList = insertInDatabaseArtist(generateData, this.countEntries);
        List<Genre> genreList = insertInDatabaseGenres(generateData, this.countEntries);
        List<Album> albumList = insertInDatabaseAlbums(artistList, genreList, generateData, this.countEntries);
        testArtist();
        testGenre();
        testAlbum();
    }

    /**
     * * Insert in database a lot of artists
     * @param generateData
     * @param countEntries
     * @return
     */
    public List<Artist> insertInDatabaseArtist(GenerateData generateData, int countEntries) {
        List<Artist> artistList = generateData.createArtist(countEntries);
        artistRepository.saveAll(artistList);
        return artistList;
    }

    /**
     * * Insert in database a lot of genres
     * @param generateData
     * @param countEntries
     * @return
     */
    public List<Genre> insertInDatabaseGenres(GenerateData generateData, int countEntries) {
        List<Genre> genreList = generateData.createGenres(countEntries);
        genreRepository.saveAll(genreList);
        return genreList;
    }

    /**
     *
     * Insert in database a lot of albums
     * @param artists
     * @param genres
     * @param generateData
     * @param countEntries
     * @return
     */
    public List<Album> insertInDatabaseAlbums(List<Artist> artists, List<Genre> genres, GenerateData generateData, int countEntries) {
        List<Album> albumList = generateData.createAlbums(artists, genres, countEntries);
        albumRepository.saveAll(albumList);
        return albumList;
    }

    /**
     * This method test some functionalities of the ArtistRepository
     */
    public void testArtist() {

        try {
            Random random = new Random();
            BufferedWriter informationWriter = new BufferedWriter(new FileWriter("informationArtist.txt", true));
            BufferedWriter logWriter = new BufferedWriter(new FileWriter("logsArtist.txt", true));
            long start = System.currentTimeMillis();
            List<Artist> artists = artistRepository.findAll();
            long end = System.currentTimeMillis();
            long timeExecution = end-start;
            logWriter.write("\tTimp de executie : " + timeExecution + " ms\n");
            informationWriter.write("\tArtisti gasiti in baza de date: \n");
            for (Artist artist : artists) {
                informationWriter.write(artist.toString());
                informationWriter.write("\n");
            }
            informationWriter.write("\n");
            start = System.currentTimeMillis();
            Artist randomArtist = artists.get(random.nextInt(this.countEntries));
            List<Artist> artist = artistRepository.findByName(randomArtist.getName());
            end = System.currentTimeMillis();
            timeExecution = end-start;
            logWriter.write("\tTimp de executie : " + timeExecution + " ms\n");
            informationWriter.write("\tArtisti gasiti in baza de date cu numele dat: \n");
            for(Artist artist1: artist){
                informationWriter.write(artist1.toString());
                informationWriter.write("\n");
            }
            informationWriter.close();
            logWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * This method test some functionalities of the AlbumRepository
     */
    public void testAlbum() {
        try {
            Random random = new Random();
            BufferedWriter informationWriter = new BufferedWriter(new FileWriter("informationAlbum.txt", true));
            BufferedWriter logWriter = new BufferedWriter(new FileWriter("logsAlbum.txt", true));
            long start = System.currentTimeMillis();
            List<Album> randomAlbums = albumRepository.findAll();
            System.out.println(randomAlbums);
            Album randomAlbum = randomAlbums.get(random.nextInt(this.countEntries));
            Optional<Album> specificAlbum = albumRepository.findById(randomAlbum.getId());
            long end = System.currentTimeMillis();
            long timeExecution = end-start;
            System.out.println(specificAlbum);
            logWriter.write("\tTimp de executie: " + randomAlbum.getId()+ " | " + timeExecution + " ms\n");
            informationWriter.write("\tAlbum gasit " + randomAlbum.getId() + ": \n");
            informationWriter.write(specificAlbum.toString());
            informationWriter.close();
            logWriter.close();

        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * This method test some functionalities of the GenreRepository
     */
    public void testGenre() {
        try {
            Random random = new Random();
            BufferedWriter informationWriter = new BufferedWriter(new FileWriter("informationGenre.txt"));
            BufferedWriter logWriter = new BufferedWriter(new FileWriter("logsGenre.txt"));
            long start = System.currentTimeMillis();
            List<Genre> genres = genreRepository.findAll();
            long end = System.currentTimeMillis();
            long timeExecution = end-start;
            logWriter.write("\tTImp de executie: " + timeExecution + " ms\n");
            informationWriter.write("\tGenuri din baza de date: \n");
            for (Genre genre : genres) {
                informationWriter.write(genre.toString());
                informationWriter.write("\n");
            }
            informationWriter.write("\n");
            start = System.currentTimeMillis();
            Optional<Genre> specificGenre = Optional.ofNullable(genreRepository.findById(random.nextInt(this.countEntries)));
            end = System.currentTimeMillis();
            timeExecution = end-start;
            logWriter.write("\tTimp de executie : " + timeExecution + " ms\n");
            informationWriter.write("\tGen gasit "+ specificGenre.toString() + " :\n");
            informationWriter.write(specificGenre.toString());
            informationWriter.close();
            logWriter.close();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

    }
}