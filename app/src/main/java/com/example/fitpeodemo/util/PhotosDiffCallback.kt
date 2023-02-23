package com.example.fitpeodemo.util

import androidx.recyclerview.widget.DiffUtil
import com.example.fitpeodemo.domain.model.PhotoModel
import javax.inject.Inject

class PhotosDiffCallback @Inject constructor(): DiffUtil.ItemCallback<PhotoModel>() {
    override fun areItemsTheSame(oldItem: PhotoModel, newItem: PhotoModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PhotoModel, newItem: PhotoModel): Boolean {
        return oldItem.id == newItem.id
    }
}