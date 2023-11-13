package no.solberg.backend.services.compare;

import no.solberg.backend.exceptions.UserNotFoundException;
import no.solberg.backend.models.SpotifyUser;
import no.solberg.backend.models.SpotifyUserArtist;
import no.solberg.backend.repositories.ArtistRepository;
import no.solberg.backend.repositories.SpotifyUserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class CompareServiceImpl implements CompareService {
    private final SpotifyUserRepository userRepository;
    private final ArtistRepository artistRepository;

    public CompareServiceImpl(SpotifyUserRepository userRepository, ArtistRepository artistRepository) {
        this.userRepository = userRepository;
        this.artistRepository = artistRepository;
    }


    @Override
    public List<ArtistComparison> getArtistComparisons(String userA, String userB) {
        SpotifyUser spotifyUserA = userRepository.findById(userA)
                .orElseThrow(() -> new UserNotFoundException(userA));
        SpotifyUser spotifyUserB = userRepository.findById(userB)
                .orElseThrow(() -> new UserNotFoundException(userB));

        Set<SpotifyUserArtist> spotifyUserArtistsA = spotifyUserA.getSpotifyUserArtists();
        Set<SpotifyUserArtist> spotifyUserArtistsB = spotifyUserB.getSpotifyUserArtists();

        List<ArtistComparison> result = new ArrayList<ArtistComparison>();

        for (SpotifyUserArtist spotifyUserArtistA : spotifyUserArtistsA) {
            String artistAId = spotifyUserArtistA.getArtist().getArtistId();
            for (SpotifyUserArtist spotifyUserArtistB : spotifyUserArtistsB) {
                if (Objects.equals(artistAId, spotifyUserArtistB.getArtist().getArtistId())) {
                    ArtistComparison artistComparison = new ArtistComparison(artistAId,
                            spotifyUserArtistA.getArtist().getArtistName(),
                            spotifyUserArtistA.getArtistRank(),
                            spotifyUserArtistB.getArtistRank());
                    result.add(artistComparison);
                }
            }
        }

        return result;
    }
}
