package no.solberg.backend.models.dtos.genre;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenrePostDTO {
    @NotNull
    @NotBlank
    private String genreName;
}
