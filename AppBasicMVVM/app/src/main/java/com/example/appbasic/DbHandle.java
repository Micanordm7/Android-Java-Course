package com.example.appbasic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.ArrayList;
import java.util.List;

@Database(entities = {User.class}, version = 1)
public abstract class DbHandle extends RoomDatabase {
    public static volatile DbHandle INSTANCE;
    public abstract UserDao getUserDao();

    public static DbHandle getInstance(Context context){
        if (INSTANCE == null){
            synchronized (DbHandle.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DbHandle.class,"boutique.bd")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }

        return INSTANCE;
    }

}
