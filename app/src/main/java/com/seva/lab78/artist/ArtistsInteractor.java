

package com.seva.lab78.artist;

import com.seva.lab78.data.api.client.SpotifyService;
import io.reactivex.Observable;
import java.util.List;

public class ArtistsInteractor implements com.seva.lab78.artist.ArtistContract.Interactor {

  private SpotifyService spotifyService;

  public ArtistsInteractor(SpotifyService spotifyService) {
    this.spotifyService = spotifyService;
  }

  @Override
  public Observable<List<com.seva.lab78.artist.model.Artist>> searchArtists(String query) {
    return spotifyService.search(query);
  }

}
