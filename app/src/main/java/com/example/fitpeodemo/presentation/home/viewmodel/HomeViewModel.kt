package com.example.fitpeodemo.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.fitpeodemo.domain.model.PhotoModel
import com.example.fitpeodemo.domain.use_case.GetPhotosUseCase
import com.example.fitpeodemo.util.NetworkMonitor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPhotosUseCase: GetPhotosUseCase,
    networkMonitor: NetworkMonitor
): ViewModel() {

    val isOnline = networkMonitor.isOnline.shareIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
    )

    suspend fun getPhotos(): Flow<PagingData<PhotoModel>> = getPhotosUseCase()

}