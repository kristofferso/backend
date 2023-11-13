package no.solberg.backend.models;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SpotifyUserArtistId implements Serializable {

    private String spotifyUserId;
    private String artistId;

    public SpotifyUserArtistId() {}

    public SpotifyUserArtistId(String spotifyUserId, String artistId) {
        this.artistId = artistId;
        this.spotifyUserId = spotifyUserId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpotifyUserArtistId that = (SpotifyUserArtistId) o;
        return Objects.equals(spotifyUserId, that.spotifyUserId) && Objects.equals(artistId, that.artistId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(spotifyUserId, artistId);
    }
}
