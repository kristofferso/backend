package no.solberg.backend.models.dtos.spotifyUser;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpotifyUserPostDTO {
    @NotNull
    @NotBlank
    private String spotifyUserId;
    private String name;
    private String email;
    private String accessToken;
}