package no.solberg.backend.models.dtos.artist;

import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.util.Set;

@Getter
@Setter
public class ArtistGetDTO {
    private String artistId;
    private String artistName;
    private int popularity;
    private URL imageUrl;
    private URL externalUrl;
    private Set<String> spotifyUserIds;
}
