package com.yulin.ivan.gurutest.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.yulin.ivan.gurutest.data.entity.Photo;

/**
 * Created by Ivan Y. on 2020-02-24.
 */
@Database(entities = {Photo.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PhotoDao photoDao();

    private static volatile AppDatabase INSTANCE;


    static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "app_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
