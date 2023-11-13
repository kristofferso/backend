package no.solberg.backend.services.genre;

import no.solberg.backend.exceptions.GenreNotFoundException;
import no.solberg.backend.models.Genre;
import no.solberg.backend.repositories.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }
    @Override
    public Genre findById(Integer id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new GenreNotFoundException(id));
    }

    @Override
    public Collection<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public Genre add(Genre entity) {
        return genreRepository.save(entity);
    }
}
