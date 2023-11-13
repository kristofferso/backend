package no.solberg.backend.repositories;

import no.solberg.backend.models.SpotifyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpotifyUserRepository extends JpaRepository<SpotifyUser, String> {
}
