package com.s.popularimageapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.s.popularimageapp.R
import com.s.popularimageapp.adapter.MainAdapter
import com.s.popularimageapp.api.RetrofitInstance
import com.s.popularimageapp.model.Datum
import com.s.popularimageapp.utils.Status
import com.s.popularimageapp.viewmodel.DetailViewModel
import com.s.popularimageapp.viewmodel.MainViewModel
import com.s.popularimageapp.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_images.*

class ImagesFragment : Fragment() {

    companion object {
        fun newInstance() = ImagesFragment()
    }

    private val mainViewModel by viewModels<MainViewModel> {
        ViewModelFactory(RetrofitInstance.apiService)
    }

    private val detailViewModel by activityViewModels<DetailViewModel>()

    private lateinit var adapter: MainAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_images, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupObservers()
    }

    private fun setupUI() {
        recyclerView.layoutManager = GridLayoutManager(
            activity,
            2,
            LinearLayoutManager.VERTICAL,
            false
        )
        adapter = MainAdapter(arrayListOf()) { data ->
            detailViewModel.updateData(data)
            activity?.apply {
               supportFragmentManager.beginTransaction()
                   .replace(R.id.fragment, DetailFragment.newInstance())
                   .addToBackStack(null)
                   .commit()
           }
        }
        recyclerView.adapter = adapter
    }

    private fun setupObservers() {
        mainViewModel.getData().observe(viewLifecycleOwner, Observer { it ->
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
                        Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun retrieveList(data: List<Datum>?) {
        data?.let{
            adapter.apply {
                addData(it)
                notifyDataSetChanged()
            }
        }
    }
}