package com.example.lab_91.Repositories;

import com.example.lab_91.Entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * this interface represent the repo for the genre class
 */
@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {
    public List<Genre> findByName(String name);

    public Genre findById(int id);
}
