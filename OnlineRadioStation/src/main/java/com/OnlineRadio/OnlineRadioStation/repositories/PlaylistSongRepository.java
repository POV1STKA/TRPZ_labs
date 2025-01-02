package com.OnlineRadio.OnlineRadioStation.repositories;

import com.OnlineRadio.OnlineRadioStation.models.PlaylistSong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaylistSongRepository extends JpaRepository<PlaylistSong, Long> {

    List<PlaylistSong> findByPlaylistId(long playlistId);
}
