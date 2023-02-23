package com.example.fitpeodemo.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PhotoModel(
    val albumId: Int,
    val id: Int,
    val url: String,
    val thumbnailUrl: String,
    val title: String
): Parcelable