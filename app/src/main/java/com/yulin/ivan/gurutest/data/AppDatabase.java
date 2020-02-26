package com.yulin.ivan.gurutest.data;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * Created by Ivan Y. on 2020-02-24.
 */

public abstract class AppDatabase extends RoomDatabase {
    public abstract PhotoDao photoDao();

    private static volatile AppDatabase INSTANCE;


    static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "app_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
