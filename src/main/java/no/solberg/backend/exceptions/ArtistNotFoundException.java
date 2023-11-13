package no.solberg.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ArtistNotFoundException extends RuntimeException {
    public ArtistNotFoundException(String artistId) {
        super("Artist with id " + artistId + " was not found.");
    }
}
