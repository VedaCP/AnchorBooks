package com.example.anchorbooks

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AnchorBooksAPI {

    @GET ("books")
    suspend fun fetchAnchorBooksList(): Response<List<AnchorBooksEntity>>

    @GET("bookDetail/{bookDetail}")
    suspend fun fetchAnchorBookDetail(@Path("bookDetail") bookDetail : Int)
    : Response<List<Int>>
}