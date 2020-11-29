package com.s.popularimageapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.s.popularimageapp.R
import com.s.popularimageapp.model.Data
import kotlinx.android.synthetic.main.row_layout.view.*


class MainAdapter(private val data: ArrayList<Data>): RecyclerView.Adapter<MainAdapter.DataViewHolder>(){

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(data: Data) {
            itemView.apply {
                Glide.with(imageView.context)
                    .load(data.images.original.url)
                    .into(imageView)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false))

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun addData(data: List<Data>) {
        this.data.apply {
            clear()
            addAll(data)
        }

    }
}