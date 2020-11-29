package com.s.popularimageapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.s.popularimageapp.R
import com.s.popularimageapp.model.Datum
import kotlinx.android.synthetic.main.row_layout.view.*

class MainAdapter(private val data: ArrayList<Datum>,
                  private val onItemClicked: (Datum) -> Unit)
    : RecyclerView.Adapter<MainAdapter.DataViewHolder>(){

    class DataViewHolder(itemView: View,
                         onItemClicked: (Int) -> Unit)
        : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener{
                onItemClicked(adapterPosition)
            }
        }

        fun bind(data: Datum) {
            itemView.apply {
                Glide.with(imageView.context)
                    .load(data.images.fixed_height_downsampled.url)
                    .into(imageView)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false)) {
            onItemClicked(data[it])
        }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun addData(data: List<Datum>) {
        this.data.apply {
            clear()
            addAll(data)
        }

    }
}