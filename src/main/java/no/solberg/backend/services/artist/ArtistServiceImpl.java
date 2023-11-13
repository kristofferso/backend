package no.solberg.backend.services.artist;

import no.solberg.backend.exceptions.ArtistNotFoundException;
import no.solberg.backend.models.Artist;
import no.solberg.backend.repositories.ArtistRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ArtistServiceImpl implements ArtistService {
    private final ArtistRepository artistRepository;

    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public Artist findById(String artistId) {
        return artistRepository.findById(artistId)
                .orElseThrow(() -> new ArtistNotFoundException(artistId));
    }

    @Override
    public Collection<Artist> findAll() {
        return artistRepository.findAll();
    }

    @Override
    public Artist add(Artist entity) {
        return artistRepository.save(entity);
    }
}
