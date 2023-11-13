package no.solberg.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SpotifyUserArtist {

    @EmbeddedId
    private SpotifyUserArtistId id;

    @ManyToOne
    @MapsId("artistId")
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @ManyToOne
    @MapsId("spotifyUserId")
    @JoinColumn(name = "spotify_user_id")
    private SpotifyUser spotifyUser;

    private int artistRank;
}