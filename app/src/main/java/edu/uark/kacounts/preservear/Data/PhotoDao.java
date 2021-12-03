package edu.uark.kacounts.preservear.Data;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface PhotoDao {
    /**
     * Insert a Photo into the table
     * @return row ID for newly inserted data
     */
    @Insert
    long insert(Photo photo);

    /**
     * select all Photos
     * @return A {@link Cursor} of all Photos in the table
     */
    @Query("SELECT * FROM Photo")
    Cursor findAll();

    /**
     * Delete a Photo by ID
     * @return A number of Photos deleted
     */
    @Query("DELETE FROM Photo WHERE id = :id ")
    int delete(long id);

    /**
     * Update the Photo
     * @return A number of Photos updated
     */
    @Update
    int update(Photo photo);
}
