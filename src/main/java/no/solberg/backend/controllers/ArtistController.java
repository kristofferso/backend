package no.solberg.backend.controllers;

import jakarta.validation.Valid;
import no.solberg.backend.mappers.ArtistMapper;
import no.solberg.backend.models.Artist;
import no.solberg.backend.models.dtos.artist.ArtistPostDTO;
import no.solberg.backend.services.artist.ArtistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("api/v1/artists")
public class ArtistController {
    private final ArtistService artistService;
    private final ArtistMapper artistMapper;

    public ArtistController(ArtistService artistService, ArtistMapper artistMapper) {
        this.artistService = artistService;
        this.artistMapper = artistMapper;
    }

    @GetMapping
    public ResponseEntity findAll() {
        return ResponseEntity.ok(
                artistMapper.artistsToArtistListDTOs(artistService.findAll())
        );
    }

    @GetMapping("{artistId}")
    public ResponseEntity findByArtistId(@PathVariable String artistId) {
        return ResponseEntity.ok(
                artistMapper.artistToArtistGetDTO(artistService.findById(artistId))
        );
    }

    @PostMapping
    public ResponseEntity add(@Valid @RequestBody ArtistPostDTO entity) throws URISyntaxException {
        Artist artist = artistService.add(artistMapper.artistPostDTOToArtist(entity));
        URI uri = new URI("api/v1/artists/" + artist.getArtistId());
        return ResponseEntity.created(uri).build();
    }
}
