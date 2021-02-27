package com.example.anchorbooks

import android.util.Log
import androidx.lifecycle.LiveData

class AnchorBooksRepository(private val dao: AnchorBooksDAO) {

    val listAnchorBooks: LiveData<List<AnchorBooksEntity>> = dao.getAllAnchorBooksDaoDB()

    //val listDetailBook = dao.getAllDetailBook() //All fav image

    fun converter(converter: List<BooksList>) : List<AnchorBooksEntity> {
        val converterABEntity = mutableListOf<AnchorBooksEntity>()
        converter.map {
            converterABEntity.add(AnchorBooksEntity(id = it.id, author = it.author,
                country = it.country, imageLink = it.imageLink, language = it.language,
                title = it.title))
        }
        return converterABEntity
    }

    fun bookDetail(BookDetail: List<BookDetail>, bookDetail:Int)
    : List<BookDetail> {
        val listBookDetail : MutableList<BookDetail> = mutableListOf()
        listBookDetail.map {
            listBookDetail.add(BookDetail(id = bookDetail, author = it.author,
                country = it.country, imageLink = it.imageLink, language = it.language,
                link = it.link, pages = it.pages, title = it.title, year = it.year,
                price = it.price, lastPrice = it.lastPrice, delivery = it.delivery))
        }
        return listBookDetail
    }
    suspend fun getAnchorBooksWhitCoroutines(){
        Log.d("REPOSITORY", "UTILIZANDO COROUTINES")
        try {
            val response = AnchorBooksRetrofitClient.retrofitInstance().fetchAnchorBooksList()
            when (response.isSuccessful){
                true -> response.body()?.let {
                    dao.insertAllAnchorBooksDao(converter(it.))
                }
            }
        }
    }
}