package com.s.popularimageapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.s.popularimageapp.api.RetrofitInstance
import com.s.popularimageapp.adapter.MainAdapter
import com.s.popularimageapp.model.Datam
import com.s.popularimageapp.utils.Status
import com.s.popularimageapp.viewmodel.MainViewModel
import com.s.popularimageapp.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory(RetrofitInstance.apiService)
    }

    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI()
        setupObservers()

    }

    private fun setupUI() {
        recyclerView.layoutManager = GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false)
        adapter = MainAdapter(arrayListOf()) { data ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("imageUrl", data.images.downsized.url)
            intent.putExtra("rating", data.rating)
            intent.putExtra("source", data.source)
            intent.putExtra("display_name", data.user?.display_name)
            startActivity(intent)
        }
        recyclerView.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.getData().observe(this, Observer { it ->
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        resource.data?.let(::retrieveList)
                    }
                    Status.ERROR -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun retrieveList(data: List<Datam>?) {
        data?.let{
            adapter.apply {
                addData(it)
                notifyDataSetChanged()
            }
        }
    }
}