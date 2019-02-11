package com.seva.lab78.track;

import android.content.Context;
import android.content.Intent;

import com.seva.lab78.artist.Artist;
import com.seva.lab78.track.model.Track;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Zava on 21.11.2018.
 */

public interface TrackContract {

   interface View extends com.seva.lab78.base.Presenter.View {

    void showLoading();

    void hideLoading();

    void showTracksNotFoundMessage();

    void showConnectionErrorMessage();

    void renderTracks(List<Track> tracks);

    void launchTrackDetail(List<Track> tracks, Track track, int position);

     Intent getStartIntent(Context context, List<Track> tracks, Track track, int position);
  }

  interface Presenter {
    void onSearchTracks(String string);

    void launchTrackDetail(List<Track> tracks, Track track, int position);
  }

  interface Interactor {
     Observable<List<Track>> loadData(String artistId);
  }
}
