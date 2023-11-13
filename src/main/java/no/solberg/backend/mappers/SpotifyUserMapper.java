package no.solberg.backend.mappers;

import no.solberg.backend.models.Artist;
import no.solberg.backend.models.SpotifyUser;
import no.solberg.backend.models.SpotifyUserArtist;
import no.solberg.backend.models.dtos.spotifyUser.SpotifyUserGetDTO;
import no.solberg.backend.models.dtos.spotifyUser.SpotifyUserListDTO;
import no.solberg.backend.models.dtos.spotifyUser.SpotifyUserPostDTO;
import org.mapstruct.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface SpotifyUserMapper {
    @Named(value = "spotifyUserArtistsToArtistIds")
    default HashMap<Integer, String> map(Set<SpotifyUserArtist> value) {
        if(value == null)
            return null;
        HashMap<Integer, String> result = new HashMap<Integer, String>();
        for (SpotifyUserArtist spotifyUserArtist : value) {
            result.put(spotifyUserArtist.getArtistRank(), spotifyUserArtist.getArtist().getArtistId());
        }
        return result;
    }

    @Mapping(source = "spotifyUserArtists", target = "topArtistIds", qualifiedByName = "spotifyUserArtistsToArtistIds")
    SpotifyUserGetDTO spotifyUserToSpotifyUserGetDTO(SpotifyUser spotifyUser);

    Collection<SpotifyUserListDTO>  spotifyUsersToSpotifyUserListDTOs(Collection<SpotifyUser> spotifyUsers);

    SpotifyUser spotifyUserPostDTOToSpotifyUser(SpotifyUserPostDTO spotifyUserPostDTO);
}
