package ait.album.dao;

import ait.album.model.Photo;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;

public class AlbumImpl implements Album {
    private Photo[] photos;
    private int size;

    public AlbumImpl(int capacity) {
        this.photos = new Photo[capacity];
    }

    Comparator<Photo> comparator = (p1, p2) -> {
        int res = Integer.compare(p1.getPhotoId(), p2.getPhotoId());
        return res != 0 ? res : Integer.compare(p1.getAlbumId(), p2.getAlbumId());
    };

    @Override
    public boolean addPhoto(Photo photo) {
//        todo нужно увеличивать массив когда он заполнен???
        if (photos.length == size) return false;
        if (photo == null) return false;
        if (getPhotoFromAlbum(photo.getPhotoId(), photo.getAlbumId()) != null) return false;
        photos[size++] = photo;
        return true;
    }

    @Override
    public boolean removePhoto(int photoId, int albumId) {
        int indexPhoto = getPhotoIndex(photoId, albumId);
        if (indexPhoto != -1) {
            for (int i = indexPhoto; i < size - 1; i++) {
                photos[i] = photos[i + 1];
            }
            photos[size - 1] = null;
            size--;
            return true;
        }
        return false;
    }


    private int getPhotoIndex(int photoId, int albumId) {
        Photo res = getPhotoFromAlbum(photoId, albumId);
        if (res == null) return -1;
        for (int i = 0; i < size; i++) {
            if (photos[i].equals(res)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean updatePhoto(int photoId, int albumId, String url) {
        Photo res = getPhotoFromAlbum(photoId, albumId);
        if (res != null) {
            res.setUrl(url);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Photo getPhotoFromAlbum(int photoId, int albumId) {
        if (size == 0) return null;
        for (int i = 0; i < size; i++) {
            if (photos[i].getPhotoId() == photoId && photos[i].getAlbumId() == albumId) {
                return photos[i];
            }
        }
        return null;
    }

    @Override
    public Photo[] getAllPhotoFromAlbum(int albumId) {
        int j = 0;
        Photo[] res = Arrays.copyOf(photos, size);
        for (int i = 0; i < size; i++) {
            if (photos[i].getAlbumId() == albumId) {
                res[j] = photos[i];
                j++;
            }
        }
        res = Arrays.copyOf(res, j);
        Arrays.sort(res, comparator);
        return res;
    }


    @Override
    public Photo[] getPhotoBetweenDate(LocalDate dateFrom, LocalDate dateTo) {
//      todo не могу понять в чум проблема!!!
        Photo[] result = Arrays.copyOf(photos, size);

        Arrays.sort(result, comparator);

        int index = 0;
        for (int i = 0; i < size; i++) {
            LocalDate photoDate = result[i].getDate().toLocalDate();

            if (photoDate.isAfter(dateFrom) && photoDate.isBefore(dateTo)) {
                result[index] = photos[i];
                index++;
            }
        }
        result = Arrays.copyOf(result, index);
        return result;
    }


    @Override
    public int size() {
        return size;
    }
}
