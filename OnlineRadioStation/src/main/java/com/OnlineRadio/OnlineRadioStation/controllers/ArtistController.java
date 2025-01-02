package com.OnlineRadio.OnlineRadioStation.controllers;

import com.OnlineRadio.OnlineRadioStation.facade.OnlineRadioFacade;
import com.OnlineRadio.OnlineRadioStation.models.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/artists")
public class ArtistController {

    private final OnlineRadioFacade facade;

    @Autowired
    public ArtistController(OnlineRadioFacade facade) {
        this.facade = facade;
    }

    @GetMapping("/all")
    public List<Artist> getAllArtists() {
        return facade.getAllArtists();
    }

    @GetMapping
    public List<Artist> searchArtistsByName(@RequestParam(required = false) String name) {
        return (name != null && !name.isEmpty())
                ? facade.searchArtistsByName(name)
                : facade.getAllArtists();
    }

    @GetMapping("/{id}")
    public Artist getArtistById(@PathVariable Long id) {
        return facade.getArtistById(id);
    }

    @PostMapping
    public Artist addOrUpdateArtist(@RequestBody Artist artist) {
        return facade.addOrUpdateArtist(artist);
    }

    @DeleteMapping("/{id}")
    public void deleteArtist(@PathVariable Long id) {
        facade.deleteArtist(id);
    }
}
