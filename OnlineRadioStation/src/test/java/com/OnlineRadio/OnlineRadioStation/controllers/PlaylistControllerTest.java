package com.OnlineRadio.OnlineRadioStation.controllers;

import com.OnlineRadio.OnlineRadioStation.facade.OnlineRadioFacade;
import com.OnlineRadio.OnlineRadioStation.models.Playlist;
import com.OnlineRadio.OnlineRadioStation.models.Song;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PlaylistController.class)
public class PlaylistControllerTest {
/**
 @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OnlineRadioFacade facade;

    @Test
    public void testCreatePlaylist() throws Exception {
        Playlist mockPlaylist = new Playlist("1", "My Playlist", "user1", Playlist.Status.PUBLIC);
        when(facade.createPlaylist("My Playlist", "user1", Playlist.Status.PUBLIC)).thenReturn(mockPlaylist);

        mockMvc.perform(post("/api/playlists")
                        .param("name", "My Playlist")
                        .param("ownerId", "user1")
                        .param("status", "PUBLIC"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("My Playlist"));
    }

    @Test
    public void testDeletePlaylist() throws Exception {
        mockMvc.perform(delete("/api/playlists/1"))
                .andExpect(status().isOk());

        verify(facade, times(1)).deletePlaylist("1");
    }

    @Test
    public void testGetSongsFromPlaylist() throws Exception {
        List<Song> songs = List.of(new Song("1", "Song A"), new Song("2", "Song B"));
        when(facade.getSongsFromPlaylist("1")).thenReturn(songs);

        mockMvc.perform(get("/api/playlists/1/songs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].title").value("Song A"));
    }*/
}
