package no.solberg.backend.services.compare;

import java.util.List;

public interface CompareService {
    List<ArtistComparison> getArtistComparisons(String userA, String userB);
}
