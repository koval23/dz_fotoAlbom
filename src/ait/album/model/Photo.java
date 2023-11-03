package ait.album.model;

import java.time.LocalDateTime;

public class Photo {
    private int albumId;
    private int photoId;
    private String title;
    private String url;
    private LocalDateTime date;

    public Photo(int albumId, int photoId, String title, String url, LocalDateTime date) {
        this.albumId = albumId;
        this.photoId = photoId;
        this.title = title;
        this.url = url;
        this.date = date;
    }

    public int getAlbumId() {
        return albumId;
    }

    public int getPhotoId() {
        return photoId;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Photo{");
        sb.append("albumId=").append(albumId);
        sb.append(", photoId=").append(photoId);
        sb.append(", title='").append(title).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append(", date=").append(date);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Photo photo = (Photo) o;

        if (albumId != photo.albumId) return false;
        return photoId == photo.photoId;
    }

    @Override
    public int hashCode() {
        int result = albumId;
        result = 31 * result + photoId;
        return result;
    }
}
