

package com.seva.lab78.track.model;

import com.google.gson.annotations.SerializedName;
import com.seva.lab78.artist.model.ArtistImage;

import java.util.List;

import static com.seva.lab78.data.api.Constants.Serialized.IMAGES;
import static com.seva.lab78.data.api.Constants.Serialized.NAME;

public class Album {
  @SerializedName(NAME)
  private String mAlbumName;
  @SerializedName(IMAGES)
  private List<com.seva.lab78.artist.model.ArtistImage> mTrackImages;

  public String getAlbumName() {
    return mAlbumName;
  }

  public void setAlbumName(String mAlbumName) {
    this.mAlbumName = mAlbumName;
  }

  public List<ArtistImage> getTrackImages() {
    return mTrackImages;
  }

  public void setTrackImages(List<ArtistImage> mTrackImages) {
    this.mTrackImages = mTrackImages;
  }

}
