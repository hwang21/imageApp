package com.s.popularimageapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.s.popularimageapp.R
import com.s.popularimageapp.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment: Fragment() {

    private val viewModel by activityViewModels<DetailViewModel>()

    companion object {
        fun newInstance() = DetailFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = viewModel.data()
        val imageUrl: String?  = data.images.fixed_height_downsampled.url
        val rating: String? = data.rating
        val source: String? = data.source
        val displayName: String? = data.user?.display_name
        setupUI(imageUrl, rating, source, displayName)
    }

    private fun setupUI(imageUrl: String?, _rating: String?, _source: String?, displayName: String?) {
        imageUrl?.apply {
            Glide.with(imageView_large.context)
                .load(imageUrl)
                .into(imageView_large)
        }
        display_name.text = displayName
        rating.text = _rating
        source.text = _source
    }
}