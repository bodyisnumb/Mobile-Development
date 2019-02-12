

package com.seva.lab78.artist;

import android.content.Context;

import io.reactivex.disposables.Disposable;

public class ArtistsPresenter extends com.seva.lab78.base.Presenter<com.seva.lab78.artist.ArtistContract.View>
    implements com.seva.lab78.artist.ArtistContract.Presenter {

  private ArtistsInteractor interactor;

  public ArtistsPresenter(ArtistsInteractor interactor) {
    this.interactor = interactor;
  }

  @Override
  public void onSearchArtist(String name) {
    getView().showLoading();
    Disposable disposable = interactor.searchArtists(name).subscribe(artists -> {
      if (artists.isEmpty()) {
        getView().showArtistNotFoundMessage();
      } else {
        getView().hideLoading();
        getView().renderArtists(artists);

      }
    }, Throwable::printStackTrace);

    addDisposableObserver(disposable);
  }

  @Override
  public void launchArtistDetail(Context context, Artist artist) {
    getView().getStartIntent(context,artist);
  }

  @Override
  public void terminate() {
    super.terminate();
    setView(null);
  }


}
