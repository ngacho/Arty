package com.example.jsonplaceholder.di

import com.example.jsonplaceholder.data.remote.PhotosEndPoint
import com.example.jsonplaceholder.data.remote.RemoteConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object RemoteModule {

    @Provides
    @Singleton
    fun provideRetrofitClient(): Retrofit = Retrofit
        .Builder()
        .baseUrl(RemoteConstants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideEndPoint(retrofit: Retrofit): PhotosEndPoint = retrofit.create(PhotosEndPoint::class.java)

}