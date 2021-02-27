package com.example.anchorbooks

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AnchorBooksRetrofitClient {
    companion object {
        private const val URL_BASE = "https://my-json-server.typicode.com/Himuravidal/anchorBooks/"
        fun retrofitInstance(): AnchorBooksAPI {
            val retrofitClient = Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofitClient.create(AnchorBooksAPI::class.java)
        }
    }
}