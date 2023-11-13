package no.solberg.backend.mappers;

import no.solberg.backend.models.Artist;
import no.solberg.backend.models.Genre;
import no.solberg.backend.models.SpotifyUser;
import no.solberg.backend.models.SpotifyUserArtist;
import no.solberg.backend.models.dtos.genre.GenreGetDTO;
import no.solberg.backend.models.dtos.genre.GenreListDTO;
import no.solberg.backend.models.dtos.genre.GenrePostDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface GenreMapper {
    @Named(value = "artistsToArtistIds")
    default Set<String> map(Set<Artist> value) {
        if(value == null)
            return null;
        return value.stream()
                .map(Artist::getArtistId)
                .collect(Collectors.toSet());
    }

    @Mapping(source = "artists", target = "artistIds", qualifiedByName = "artistsToArtistIds")
    GenreGetDTO genreToGenreGetDTO(Genre genre);

    Collection<GenreListDTO> genresToGenreListDTOs(Collection<Genre> genres);

    Genre genrePostDTOToGenre(GenrePostDTO genrePostDTO);
}
