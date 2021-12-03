package edu.uark.kacounts.preservear.Data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Photo.class}, version = 1, exportSchema = false)
public abstract class PhotoDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "photo_db";
    private static PhotoDatabase INSTANCE;

    public static PhotoDatabase getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context, PhotoDatabase.class,DATABASE_NAME).build();
        }
        return INSTANCE;
    }

    public abstract PhotoDao getPhotoDao();

}
