package no.solberg.backend.models.dtos.genre;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class GenreGetDTO {
    private int id;
    private String genreName;
    private Set<String> artistIds;
}
