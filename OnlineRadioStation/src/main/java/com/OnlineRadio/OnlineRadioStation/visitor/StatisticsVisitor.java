package com.OnlineRadio.OnlineRadioStation.visitor;

import com.OnlineRadio.OnlineRadioStation.models.User;
import com.OnlineRadio.OnlineRadioStation.models.Song;
import com.OnlineRadio.OnlineRadioStation.models.Playlist;

public interface StatisticsVisitor {
    void visitUser(User user);
    void visitSong(Song song);
    void visitPlaylist(Playlist playlist);
}
