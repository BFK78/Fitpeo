package com.example.fitpeodemo.domain.use_case

import androidx.paging.PagingData
import com.example.fitpeodemo.domain.model.PhotoModel
import com.example.fitpeodemo.domain.repository.PhotosRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(
    private val photosRepository: PhotosRepository
) {
    suspend operator fun invoke(): Flow<PagingData<PhotoModel>> {
        return photosRepository.getPhotos()
    }
}