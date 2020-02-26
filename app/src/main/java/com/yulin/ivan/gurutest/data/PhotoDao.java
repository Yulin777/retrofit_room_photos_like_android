package com.yulin.ivan.gurutest.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.yulin.ivan.gurutest.data.entity.Photo;

import java.util.List;

/**
 * Created by Ivan Y. on 2020-02-24.
 */
@Dao
public interface PhotoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Photo> photoList);

    @Query("SELECT * FROM photos_table ORDER BY likes DESC")
    LiveData<List<Photo>> getAllPhotos();

    @Query("SELECT * FROM photos_table WHERE id=:id LIMIT 1")
    LiveData<Photo> getPhoto(String id);

    @Query("UPDATE photos_table SET liked=:liked WHERE id=:id")
    void updateLiked(String id, boolean liked);

    @Query("UPDATE photos_table SET likes=:likes WHERE id=:id")
    void updateLikes(String id, Integer likes);

    @Query("UPDATE photos_table SET views=:views WHERE id=:id")
    void updateViews(String id, Integer views);
}
