package com.example.fitpeodemo.domain.paging.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fitpeodemo.databinding.FragmentHomeBinding
import com.example.fitpeodemo.databinding.SingleItemBinding
import com.example.fitpeodemo.domain.model.PhotoModel
import com.example.fitpeodemo.util.PhotosDiffCallback
import com.squareup.picasso.Picasso
import javax.inject.Inject

class PhotoPagingDataAdapter @Inject constructor(
     diffCallback: PhotosDiffCallback
): PagingDataAdapter<PhotoModel, PhotoPagingDataAdapter.PhotoViewHolder>(diffCallback = diffCallback) {

    private var onClick: (photoModel: PhotoModel) -> Unit = {}

    fun setOnClick(onClick: (photoModel: PhotoModel) -> Unit) {
        this.onClick = onClick
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(
            SingleItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ),
                parent,
                false
            )
        )
    }
    inner class PhotoViewHolder(
        private val binding: SingleItemBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(photoModel: PhotoModel?) {
            binding.root.setOnClickListener {
                photoModel?.let {
                    onClick(it)
                }
            }
            photoModel?.let {
                Picasso.get().load(it.thumbnailUrl).into(binding.image)
                binding.photoModel = it
            }
        }
    }
}

