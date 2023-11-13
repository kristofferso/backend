package no.solberg.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.util.Set;

@Entity
@Getter
@Setter
public class Artist {

    @Id
    private String artistId;

    private String artistName;

    private int popularity;

    private URL imageUrl;

    private URL externalUrl;

    // Relationships
    @ManyToMany(mappedBy = "artists")
    private Set<Genre> genres;

    @OneToMany(mappedBy = "artist")
    private Set<SpotifyUserArtist> spotifyUserArtists;
}
