package no.solberg.backend.controllers;

import jakarta.validation.Valid;
import no.solberg.backend.mappers.GenreMapper;
import no.solberg.backend.models.Genre;
import no.solberg.backend.models.dtos.genre.GenrePostDTO;
import no.solberg.backend.services.genre.GenreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("api/v1/genres")
public class GenreController {
    private final GenreService genreService;
    private final GenreMapper genreMapper;

    public GenreController(GenreService genreService, GenreMapper genreMapper) {
        this.genreService = genreService;
        this.genreMapper = genreMapper;
    }

    @GetMapping
    private ResponseEntity findAll() {
        return ResponseEntity.ok(
                genreMapper.genresToGenreListDTOs(genreService.findAll())
        );
    }

    @GetMapping("{id}")
    private ResponseEntity findById(@PathVariable Integer id) {
        return ResponseEntity.ok(
                genreMapper.genreToGenreGetDTO(genreService.findById(id))
        );
    }

    @PostMapping
    private ResponseEntity add(@Valid @RequestBody GenrePostDTO entity) throws URISyntaxException {
        Genre genre = genreService.add(genreMapper.genrePostDTOToGenre(entity));
        URI uri = new URI("api/v1/genres" + genre.getId());
        return ResponseEntity.created(uri).build();
    }
}
