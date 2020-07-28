package com.example.jsonplaceholder.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.jsonplaceholder.R
import com.example.jsonplaceholder.data.Resource
import com.example.jsonplaceholder.databinding.ActivityMainBinding
import com.example.jsonplaceholder.utils.PhotosAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var photosAdapter: PhotosAdapter
    private val mainActivityViewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mainBinding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        val photosRecyclerView = mainBinding.photosRecyclerview
        val progressBar = mainBinding.progressBar

        photosRecyclerView.setHasFixedSize(true)

        mainActivityViewModel.photos.observe(this
            , Observer { resource ->
                when (resource.status) {
                    Resource.Status.LOADING -> progressBar.visibility = View.VISIBLE
                    Resource.Status.SUCCESS -> {
                        progressBar.visibility = View.GONE
                        photosAdapter = resource.data?.let { PhotosAdapter(it) }!!
                        photosRecyclerView.adapter = photosAdapter
                    }
                    Resource.Status.ERROR -> {
                        progressBar.visibility = View.GONE
                        Log.e(
                            "Main Activity",
                            "onCreate: Network called failed because ${resource.message}"
                        )
                        Toast.makeText(
                            this,
                            "It is not you, it is us. Please give it another try",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            })
    }
}