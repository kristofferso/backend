package no.solberg.backend.models.dtos.spotifyUser;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Set;

@Getter
@Setter
public class SpotifyUserGetDTO {
    private String spotifyUserId;
    private String name;
    private String email;
    private String accessToken;
    private HashMap<Integer, String> topArtistIds;
}
