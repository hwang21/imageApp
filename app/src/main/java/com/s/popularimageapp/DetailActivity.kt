package com.s.popularimageapp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.s.popularimageapp.api.RetrofitInstance
import com.s.popularimageapp.viewmodel.MainViewModel
import com.s.popularimageapp.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity: AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory(RetrofitInstance.apiService)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val intent = intent
        val imageUrl: String?  = intent.getStringExtra("imageUrl")
        val rating: String? = intent.getStringExtra("rating")
        val source: String? = intent.getStringExtra("source")
        val displayName: String? = intent.getStringExtra("display_name")
        setupUI(imageUrl, rating, source, displayName)
    }

    private fun setupUI(imageUrl: String?, _rating: String?, _source: String?, displayName: String?) {
        //val data = viewModel.data[position]
        imageUrl?.apply {
            Glide.with(this@DetailActivity)
                .load(imageUrl)
                .into(imageView_large)
        }
        //val user = viewModel.data[position].user
        display_name.text = displayName
        rating.text = _rating
        source.text = _source
    }
}