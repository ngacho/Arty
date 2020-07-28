package com.example.jsonplaceholder.data.repository

import com.example.jsonplaceholder.data.fetchPhotosFromSingleSource
import com.example.jsonplaceholder.data.local.PhotoDao
import com.example.jsonplaceholder.data.remote.RemoteDataSource
import javax.inject.Inject

class PhotosRepository @Inject constructor(
    private val photoDao: PhotoDao,
    private val remoteDataSource: RemoteDataSource
) {

    fun fetchPhotos() = fetchPhotosFromSingleSource(
        localPhotosCall = { photoDao.fetchAllPhotos() },
        networkCall = { remoteDataSource.getPhotosFromEndPoint() },
        saveToDBCall = { photosList -> photoDao.insertAllPhotos(photosList) })
}