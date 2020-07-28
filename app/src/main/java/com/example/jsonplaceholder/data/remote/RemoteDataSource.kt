package com.example.jsonplaceholder.data.remote

import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val photosEndPoint: PhotosEndPoint) {

    suspend fun getPhotosFromEndPoint() =
        RemoteDataManager.getPhotosFromRemote {
            photosEndPoint.getPhotos()
        }
}