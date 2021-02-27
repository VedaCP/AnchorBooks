package com.example.anchorbooks

import com.google.gson.annotations.SerializedName

data class AnchorBooksResponse(val bookList: List<BooksList>)

data class AnchorBookDetail(val bookDetail: List<BookDetail>)