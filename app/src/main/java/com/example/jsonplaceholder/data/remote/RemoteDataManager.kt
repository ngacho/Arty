package com.example.jsonplaceholder.data.remote

import android.util.Log
import com.example.jsonplaceholder.data.Resource
import com.example.jsonplaceholder.data.entity.PhotoResponse
import com.example.jsonplaceholder.data.entity.RemotePhoto
import retrofit2.Response


object RemoteDataManager {

    suspend fun getPhotosFromRemote(networkCall: suspend () -> Response<PhotoResponse>): Resource<List<RemotePhoto>> {
        try {
            val response = networkCall()
            if (response.isSuccessful) {
                if (response.body()?.hits?.isNotEmpty()!!)
                    return Resource.success(response.body()!!.hits)
            }
            return Resource.error(response.errorBody().toString())
        } catch (e: Exception) {
            Log.d("Remote Data Manager", "getPhotosFromRemote: ${e.message}")
            return Resource.error(e.message.toString())
        }
    }

}