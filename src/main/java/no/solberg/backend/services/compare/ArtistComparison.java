package no.solberg.backend.services.compare;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArtistComparison {
    private String artistId;
    private String artistName;
    private int userARank;
    private int userBRank;

    public ArtistComparison(String artistId, String artistName, int userARank, int userBRank) {
        this.artistId = artistId;
        this.artistName = artistName;
        this.userARank = userARank;
        this.userBRank = userBRank;
    }
}
