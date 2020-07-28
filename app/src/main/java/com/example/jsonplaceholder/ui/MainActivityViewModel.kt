package com.example.jsonplaceholder.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.jsonplaceholder.data.repository.PhotosRepository

class MainActivityViewModel @ViewModelInject constructor(photosRepository: PhotosRepository) :
    ViewModel() {

    val photos = photosRepository.fetchPhotos()


}