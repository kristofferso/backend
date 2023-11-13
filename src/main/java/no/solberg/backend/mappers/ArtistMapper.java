package no.solberg.backend.mappers;

import no.solberg.backend.models.Artist;
import no.solberg.backend.models.Genre;
import no.solberg.backend.models.SpotifyUser;
import no.solberg.backend.models.SpotifyUserArtist;
import no.solberg.backend.models.dtos.artist.ArtistGetDTO;
import no.solberg.backend.models.dtos.artist.ArtistListDTO;
import no.solberg.backend.models.dtos.artist.ArtistPostDTO;
import no.solberg.backend.repositories.GenreRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ArtistMapper {
    @Named(value = "spotifyUserArtistsToSpotifyUserIds")
    default Set<String> mapUserArtists(Set<SpotifyUserArtist> value) {
        if (value == null)
            return null;
        return value.stream()
                .map(SpotifyUserArtist::getSpotifyUser)
                .map(SpotifyUser::getSpotifyUserId)
                .collect(Collectors.toSet());
    }

    @Mapping(source = "spotifyUserArtists", target = "spotifyUserIds", qualifiedByName = "spotifyUserArtistsToSpotifyUserIds")
    ArtistGetDTO artistToArtistGetDTO(Artist entity);

    Map<Integer, ArtistListDTO> artistsToArtistListDTOs(Map<Integer, Artist> artists);
    Collection<ArtistListDTO> artistsToArtistListDTOs(Collection<Artist> artists);

    @Named(value = "genreNamesToGenres") // Having trouble with this. Perhaps because genres are not saved automatically
    default Set<Genre> mapGenres(String[] value) {
        if (value == null) {
            return null;
        }
        Set<Genre> genres = new HashSet<>();
        for (String genreName : value) {
            Genre genre = new Genre();
            genre.setGenreName(genreName);
            genres.add(genre);
        }
        return genres;
    }

    @Mapping(target = "genres", source = "genreNames", qualifiedByName = "genreNamesToGenres")
    Artist artistPostDTOToArtist(ArtistPostDTO artistPostDTO);
}
