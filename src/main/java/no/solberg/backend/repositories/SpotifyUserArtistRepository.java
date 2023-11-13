package no.solberg.backend.repositories;

import no.solberg.backend.models.SpotifyUserArtist;
import no.solberg.backend.models.SpotifyUserArtistId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpotifyUserArtistRepository extends JpaRepository<SpotifyUserArtist, SpotifyUserArtistId> {
}
