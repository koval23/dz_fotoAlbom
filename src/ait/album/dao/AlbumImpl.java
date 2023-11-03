package ait.album.dao;

import ait.album.model.Photo;

import java.time.LocalDate;

public class AlbumImpl implements Album {
    private Photo[] photos;
    private int size;

    public AlbumImpl(int capacity) {
        this.photos = new Photo[capacity];
    }

    @Override
    public boolean addPhoto(Photo photo) {
        return false;
    }

    @Override
    public boolean removePhoto(int photoId, int albumId) {
        return false;
    }

    @Override
    public boolean updatePhoto(int photoId, int albumId, String url) {
        return false;
    }

    @Override
    public Photo getPhotoFromAlbum(int photoId, int albumId) {
        return null;
    }

    @Override
    public Photo[] detAllPhotoFromAlbum(int albumId) {
        return new Photo[0];
    }

    @Override
    public Photo[] getPhotoBetweenDate(LocalDate dateFrom, LocalDate dateTo) {
        return new Photo[0];
    }

    @Override
    public int size() {
        return 0;
    }
}
