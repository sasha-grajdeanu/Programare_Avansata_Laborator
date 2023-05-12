package com.example.lab_91.Repositories;

import com.example.lab_91.Entities.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * this interface represent the repo for the artist class
 */
@Repository
public interface ArtistRepository extends JpaRepository<Artist, Integer> {

    public List<Artist> findByName(String name);

    public Artist findById(int id);

}
