package com.example.jsonplaceholder.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.jsonplaceholder.data.entity.Photo
import dagger.hilt.android.qualifiers.ApplicationContext

@Database(entities = [Photo::class], version = 1, exportSchema = false)
abstract class PhotoDatabase : RoomDatabase() {

    abstract fun photoDao(): PhotoDao

    companion object {

        @Volatile
        private var instance: PhotoDatabase? = null

        fun getDatabase(@ApplicationContext context: Context): PhotoDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(@ApplicationContext appContext: Context) =
            Room.databaseBuilder(appContext, PhotoDatabase::class.java, "photos")
                .fallbackToDestructiveMigration()
                .build()


    }

}