package com.OnlineRadio.OnlineRadioStation.repositories;

import com.OnlineRadio.OnlineRadioStation.models.SongArtist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongArtistRepository extends JpaRepository<SongArtist, Long> {

    List<SongArtist> findByArtistId(long artistId);

    List<SongArtist> findBySongId(long songId);

}
