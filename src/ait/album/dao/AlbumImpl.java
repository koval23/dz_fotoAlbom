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
    Comparator<Photo> comparatorData = (d1,d2)->{
        int year = Integer.compare(d1.getDate().getYear(), d2.getDate().getYear());
        if (year != 0) {
            return year;
        }
        int month = Integer.compare(d1.getDate().getMonthValue(), d2.getDate().getMonthValue());
        if (month != 0) {
            return month;
        }
        return Integer.compare(d1.getDate().getDayOfMonth(), d2.getDate().getDayOfMonth());

    };

    @Override
    public boolean addPhoto(Photo photo) {
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
//      todo ПЕРЕДЕЛАЛ!!!
        Photo[] result = new Photo[size];
        int index = 0;
        for (int i = 0; i < size; i++) {
            LocalDate photoDate = photos[i].getDate().toLocalDate();

            if (!photoDate.isBefore(dateFrom) && !photoDate.isAfter(dateTo)) {
                result[index] = photos[i];
                index++;
            }
        }
        return Arrays.copyOf(result, index);
    }


    @Override
    public int size() {
        return size;
    }
}
