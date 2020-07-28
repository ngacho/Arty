package com.example.jsonplaceholder.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jsonplaceholder.data.entity.Photo

@Dao
interface PhotoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllPhotos(photos : List<Photo>)


    @Query("SELECT * FROM photos")
    suspend fun fetchAllPhotos() : List<Photo>

}