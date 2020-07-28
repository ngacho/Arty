package com.example.jsonplaceholder.di

import android.content.Context
import com.example.jsonplaceholder.data.local.PhotoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context) = PhotoDatabase.getDatabase(appContext)

    @Provides
    @Singleton
    fun providePhotoDao(db : PhotoDatabase) = db.photoDao()

}