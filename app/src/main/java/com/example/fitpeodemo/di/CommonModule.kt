package com.example.fitpeodemo.di

import com.example.fitpeodemo.data.remote.repository.PhotosRepositoryImplementation
import com.example.fitpeodemo.domain.repository.PhotosRepository
import com.example.fitpeodemo.util.ConnectivityManager
import com.example.fitpeodemo.util.NetworkMonitor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface CommonModule {

    @Binds
    @Singleton
    fun bindPhotosRepository(photosRepositoryImplementation: PhotosRepositoryImplementation): PhotosRepository

    @Binds
    @Singleton
    fun bindNetworkMonitor(connectivityManager: ConnectivityManager): NetworkMonitor
}