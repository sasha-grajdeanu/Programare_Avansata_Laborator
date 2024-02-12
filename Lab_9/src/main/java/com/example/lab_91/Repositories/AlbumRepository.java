package com.example.lab_91.Repositories;

import com.example.lab_91.Entities.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * this interface represent the repo for the album class
 */
@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer> {
    public List<Album> findByTitle(String title);

    public Album findById(int id);
}
