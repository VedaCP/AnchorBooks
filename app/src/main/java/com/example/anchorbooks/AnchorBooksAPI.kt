package com.example.anchorbooks

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AnchorBooksAPI {

    @GET ("db")
    suspend fun fetchAnchorBooksList(): Response<AnchorBooksResponse>

    @GET("bookDetail/{id}")
    suspend fun fetchBookDetailEntity(@Path("id") id : Int)
    : Response<BookDetail>
}