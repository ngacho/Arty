package com.example.jsonplaceholder.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.jsonplaceholder.R
import com.example.jsonplaceholder.data.entity.Photo
import com.example.jsonplaceholder.databinding.PhotoItemBinding

class PhotosAdapter(private val photosList: List<Photo>) :
    RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val photoItemBinding = DataBindingUtil.inflate<PhotoItemBinding>(
            layoutInflater,
            R.layout.photo_item,
            parent,
            false
        )

        return PhotosViewHolder(photoItemBinding)
    }

    override fun getItemCount() = photosList.size

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        holder.bind(photosList[position])
    }

    class PhotosViewHolder(private val photoItemBinding: PhotoItemBinding) :
        RecyclerView.ViewHolder(photoItemBinding.root) {
        fun bind(photo: Photo) {
            photoItemBinding.photo = photo
            photoItemBinding.executePendingBindings()

        }

    }
}