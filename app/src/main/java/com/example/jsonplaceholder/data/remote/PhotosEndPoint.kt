package com.example.jsonplaceholder.data.remote

import com.example.jsonplaceholder.data.entity.PhotoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotosEndPoint {

    @GET("/api/")
    suspend fun getPhotos(
        @Query("key") apiKey: String = RemoteConstants.API_KEY,
        @Query("image_type") imageType: String = "photo",
        @Query("q") query: String = "art"
    ): Response<PhotoResponse>
}