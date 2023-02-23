package com.example.fitpeodemo.data.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.fitpeodemo.data.remote.PhotosApi
import com.example.fitpeodemo.domain.model.PhotoModel
import com.example.fitpeodemo.domain.paging.PhotosPagingSource
import com.example.fitpeodemo.domain.repository.PhotosRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PhotosRepositoryImplementation @Inject constructor(
    private val photosPagingSource: PhotosPagingSource,
    private val pagingPhotosApi: PhotosApi
): PhotosRepository {
    override suspend fun getPhotos(): Flow<PagingData<PhotoModel>> = withContext(Dispatchers.IO) {
        Pager(
            config = PagingConfig(
                pageSize = 50,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PhotosPagingSource(photosApi = pagingPhotosApi)
            }
        ).flow
    }
}