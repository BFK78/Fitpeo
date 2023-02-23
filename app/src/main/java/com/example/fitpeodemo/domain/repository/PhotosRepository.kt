package com.example.fitpeodemo.domain.repository

import androidx.paging.PagingData
import com.example.fitpeodemo.domain.model.PhotoModel
import kotlinx.coroutines.flow.Flow

interface PhotosRepository {
    suspend fun getPhotos(): Flow<PagingData<PhotoModel>>
}