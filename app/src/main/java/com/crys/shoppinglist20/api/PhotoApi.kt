package com.crys.shoppinglist20.api

import com.crys.shoppinglist20.other.Key.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoApi {
    @GET("services/rest/")
    suspend fun getPhotos(
        @Query("method")
        method: String = "flickr.photos.search",
        @Query("api_key")
        apiKey: String = API_KEY,
        @Query("tags")
        tags: String,
        @Query("per_page")
        perPageNumber: Int = 1,
        @Query("page")
        pageNumber: Int = 1,
        @Query("format")
        format: String = "json",
        @Query("nojsoncallback")
        callback: Int = 1
    ): Response<PhotoResponse>
}