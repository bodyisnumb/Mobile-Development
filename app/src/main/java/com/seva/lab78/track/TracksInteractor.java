

package com.seva.lab78.track;

import com.seva.lab78.data.api.client.SpotifyService;
import com.seva.lab78.track.model.Track;

import java.util.List;

import io.reactivex.Observable;

public class TracksInteractor implements com.seva.lab78.track.TrackContract.Interactor {

  private SpotifyService spotifyService;

  public TracksInteractor(SpotifyService spotifyService) {
    this.spotifyService = spotifyService;
  }

  public Observable<List<Track>> loadData(String artistId) {
    return spotifyService.getTracks(artistId);
  }
}
