package com.example.jsonplaceholder.data

import androidx.lifecycle.liveData
import com.example.jsonplaceholder.data.entity.Photo
import com.example.jsonplaceholder.data.entity.RemotePhoto
import kotlinx.coroutines.Dispatchers


fun fetchPhotosFromSingleSource(
    localPhotosCall: suspend () -> List<Photo>,
    networkCall: suspend () -> Resource<List<RemotePhoto>>,
    saveToDBCall: suspend (photos: List<Photo>) -> Unit
) =
    liveData(Dispatchers.IO) {
        emit(Resource.loading())

        val responseStatus = networkCall.invoke()
        when (responseStatus.status) {
            Resource.Status.SUCCESS -> responseStatus.data?.let {
                val photoList = mutableListOf<Photo>()
                it.forEach { remotePhoto ->
                    photoList.add(
                        Photo(
                            remotePhoto.id.toInt(),
                            remotePhoto.user,
                            remotePhoto.largeImageURL
                        )
                    )
                }
                saveToDBCall(photoList)
            }
            Resource.Status.ERROR -> {
                localPhotosCall().let {
                    if (it.isEmpty())
                        emit(Resource.error("Oops, We couldn't find anything"))

                    emit(Resource.success(it))
                }
                emit(Resource.error(message = responseStatus.message!!))
            }
            else -> emit(Resource.loading())
        }


        localPhotosCall().let {
            if (it.isEmpty())
                emit(Resource.error("Oops, We couldn't find anything"))

            emit(Resource.success(it))
        }
    }

