package com.seva.lab78.data.api.client;


import com.seva.lab78.track.model.Track;

public interface SpotifyService {

  io.reactivex.Observable<java.util.List<com.seva.lab78.artist.model.Artist>> search(String query);

  io.reactivex.Observable<java.util.List<Track>> getTracks(String artistId);
}
