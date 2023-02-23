package com.example.fitpeodemo.domain.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.fitpeodemo.data.remote.PhotosApi
import com.example.fitpeodemo.domain.model.PhotoModel
import javax.inject.Inject

class PhotosPagingSource @Inject constructor(
 private val photosApi: PhotosApi
): PagingSource<Int, PhotoModel>() {

    override fun getRefreshKey(state: PagingState<Int, PhotoModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotoModel> {
        val albumId = params.key ?: ALBUM_ID
        return try {
            val photos = photosApi.getPhotosByAlbumId(albumId)
            val nextAlbumId = if (photos.isEmpty()) null else albumId + 1
            LoadResult.Page(
                data = photos,
                prevKey = if (albumId == ALBUM_ID) null else albumId,
                nextKey = nextAlbumId
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val ALBUM_ID = 1
    }
}