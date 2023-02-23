package com.example.fitpeodemo.data.remote

import com.example.fitpeodemo.domain.model.PhotoModel
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotosApi {

    @GET("/photos")
    suspend fun getPhotosByAlbumId(
        @Query("albumId") albumId: Int
    ): List<PhotoModel>

}