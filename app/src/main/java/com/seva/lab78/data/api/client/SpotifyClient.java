package com.seva.lab78.data.api.client;

import com.seva.lab78.data.api.retrofit.SpotifyRetrofitClient;
import com.seva.lab78.track.model.Track;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SpotifyClient extends SpotifyRetrofitClient implements SpotifyService {

    @Override
    public io.reactivex.Observable<java.util.List<com.seva.lab78.artist.model.Artist>> search(String query) {
        return getSpotifyService().searchArtist(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public io.reactivex.Observable<java.util.List<Track>> getTracks(String artistId) {
        return getSpotifyService().getTracks(artistId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
