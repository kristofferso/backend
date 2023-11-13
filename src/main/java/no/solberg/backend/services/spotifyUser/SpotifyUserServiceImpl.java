package no.solberg.backend.services.spotifyUser;

import ch.qos.logback.core.encoder.EchoEncoder;
import no.solberg.backend.exceptions.ArtistNotFoundException;
import no.solberg.backend.exceptions.UserNotFoundException;
import no.solberg.backend.mappers.ArtistMapper;
import no.solberg.backend.models.Artist;
import no.solberg.backend.models.SpotifyUser;
import no.solberg.backend.models.SpotifyUserArtist;
import no.solberg.backend.models.SpotifyUserArtistId;
import no.solberg.backend.models.dtos.artist.ArtistPostDTO;
import no.solberg.backend.repositories.ArtistRepository;
import no.solberg.backend.repositories.SpotifyUserArtistRepository;
import no.solberg.backend.repositories.SpotifyUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class SpotifyUserServiceImpl implements SpotifyUserService {
    private final SpotifyUserRepository spotifyUserRepository;
    private final ArtistRepository artistRepository;
    private final SpotifyUserArtistRepository spotifyUserArtistRepository;
    private final ArtistMapper artistMapper;

    public SpotifyUserServiceImpl(SpotifyUserRepository spotifyUserRepository,
                                  ArtistRepository artistRepository,
                                  SpotifyUserArtistRepository spotifyUserArtistRepository,
                                  ArtistMapper artistMapper) {
        this.spotifyUserRepository = spotifyUserRepository;
        this.artistRepository = artistRepository;
        this.spotifyUserArtistRepository = spotifyUserArtistRepository;
        this.artistMapper = artistMapper;
    }

    @Override
    public SpotifyUser findById(String id) {
        return spotifyUserRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public Collection<SpotifyUser> findAll() {
        return spotifyUserRepository.findAll();
    }

    @Override
    public HashMap<Integer, Artist> findTopArtists(String id) {
        SpotifyUser spotifyUser = spotifyUserRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        Set<SpotifyUserArtist> spotifyUserArtists = spotifyUser.getSpotifyUserArtists();

        HashMap<Integer, Artist> result = new HashMap<Integer, Artist>();
        for (SpotifyUserArtist spotifyUserArtist : spotifyUserArtists) {
            result.put(spotifyUserArtist.getArtistRank(), spotifyUserArtist.getArtist());
        }
        return result;
    }

    @Override
    public SpotifyUser add(SpotifyUser entity) {
        return spotifyUserRepository.save(entity);
    }

    @Transactional
    @Override
    public void addArtists(String id, String[] artistIds) {
        SpotifyUser spotifyUser = spotifyUserRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        for (int i = 0; i < artistIds.length; i++) {
            String artistId = artistIds[i];
            Artist artist = artistRepository.findById(artistIds[i])
                    .orElseThrow(() -> new ArtistNotFoundException(artistId)); // Perhaps add it instead?

            SpotifyUserArtist spotifyUserArtist = new SpotifyUserArtist();
            spotifyUserArtist.setId(new SpotifyUserArtistId(id, artist.getArtistId()));
            spotifyUserArtist.setSpotifyUser(spotifyUser);
            spotifyUserArtist.setArtist(artist);
            spotifyUserArtist.setArtistRank(i + 1);

            spotifyUserArtistRepository.save(spotifyUserArtist);
        }
    }

    @Transactional
    @Override
    public void addArtists(String id, ArtistPostDTO[] artists) {
        String[] artistIds = new String[artists.length];

        for (int i = 0; i < artists.length; i++) {
            // check if artist exists in db, add it if not
            ArtistPostDTO artistPostDTO = artists[i];
            String artistId = artistPostDTO.getArtistId();
            try {
                Artist artistInDatabase = artistRepository.findById(artistId)
                        .orElseThrow(() -> new ArtistNotFoundException(artistId));
            } catch(Exception e) {
                Artist artistToSave = artistMapper.artistPostDTOToArtist(artistPostDTO);
                artistRepository.save(artistToSave);
            }
            artistIds[i] = artistId;
        }
        addArtists(id, artistIds);
    }
}
