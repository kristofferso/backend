package no.solberg.backend.controllers;

import no.solberg.backend.mappers.ArtistMapper;
import no.solberg.backend.models.SpotifyUser;
import no.solberg.backend.services.compare.CompareService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping(value = "api/v1/compare")
public class CompareController {
    private final CompareService compareService;

    public CompareController(CompareService compareService) {
        this.compareService = compareService;
    }

    @GetMapping
    public ResponseEntity findCommonArtists(@RequestParam String user_a, @RequestParam String user_b) {
        return ResponseEntity.ok(compareService.getArtistComparisons(user_a, user_b));
    }

}
