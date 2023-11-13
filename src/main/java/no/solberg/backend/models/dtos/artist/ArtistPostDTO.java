package no.solberg.backend.models.dtos.artist;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;

@Getter
@Setter
public class ArtistPostDTO {
    @NotNull
    @NotBlank
    private String artistId;
    private String artistName;
    private int popularity;
    private URL imageUrl;
    private URL externalUrl;
    private String[] genreNames;
}
