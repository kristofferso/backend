package no.solberg.backend.controllers;

import jakarta.validation.Valid;
import no.solberg.backend.mappers.ArtistMapper;
import no.solberg.backend.mappers.SpotifyUserMapper;
import no.solberg.backend.models.SpotifyUser;
import no.solberg.backend.models.dtos.artist.ArtistPostDTO;
import no.solberg.backend.models.dtos.spotifyUser.SpotifyUserPostDTO;
import no.solberg.backend.services.spotifyUser.SpotifyUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("api/v1/users")
public class SpotifyUserController {
    private final SpotifyUserService spotifyUserService;
    private final SpotifyUserMapper spotifyUserMapper;
    private final ArtistMapper artistMapper;

    public SpotifyUserController(SpotifyUserService spotifyUserService, SpotifyUserMapper spotifyUserMapper, ArtistMapper artistMapper) {
        this.spotifyUserService = spotifyUserService;
        this.spotifyUserMapper = spotifyUserMapper;
        this.artistMapper = artistMapper;
    }

    @GetMapping
    public ResponseEntity findAll() {
        return ResponseEntity.ok(
                spotifyUserMapper.spotifyUsersToSpotifyUserListDTOs(spotifyUserService.findAll()));
    }

    @GetMapping("{id}")
    public ResponseEntity findById(@PathVariable String id) {
        return ResponseEntity.ok(
                spotifyUserMapper.spotifyUserToSpotifyUserGetDTO(spotifyUserService.findById(id))
        );
    }

    @GetMapping("{id}/artists")
    public ResponseEntity findTopArtists(@PathVariable String id) {
        return ResponseEntity.ok(
                artistMapper.artistsToArtistListDTOs(spotifyUserService.findTopArtists(id))
        );
    }

    @PostMapping
    public ResponseEntity add(@Valid @RequestBody SpotifyUserPostDTO entity) throws URISyntaxException {
        SpotifyUser spotifyUser = spotifyUserService.add(spotifyUserMapper.spotifyUserPostDTOToSpotifyUser(entity));
        URI uri = new URI("api/v1/users/" + spotifyUser.getSpotifyUserId());
        return ResponseEntity.created(uri).build();
    }

    // Updates top artists for a user by giving an array of artistIds
    @PutMapping ("{id}/artists")
    public ResponseEntity addTopArtists(@PathVariable String id, @RequestBody String[] artistIds) {
        spotifyUserService.addArtists(id, artistIds);
        return ResponseEntity.noContent().build();
    }

    // Adds a users top artists with the artist information
    // TODO: Add genres as well
    @PostMapping("{id}/artists")
    public ResponseEntity addTopArtists(@PathVariable String id, @RequestBody ArtistPostDTO[] entity) {
        spotifyUserService.addArtists(id, entity);
        return ResponseEntity.noContent().build();
    }
}