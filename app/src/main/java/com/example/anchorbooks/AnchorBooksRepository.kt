package com.example.anchorbooks

import android.util.Log
import androidx.lifecycle.LiveData

class AnchorBooksRepository(private val dao: AnchorBooksDao) {

    val listABooks: LiveData<List<AnchorBooksEntity>> = dao.getAllAnchorBooksDaoDB()

    //val listDetailBook = dao.getAllDetailBook() //All fav image

    fun converter(converter: List<BooksList>): List<AnchorBooksEntity> {
        val converterABEntity: MutableList<AnchorBooksEntity> = mutableListOf()
        converter.map {
            converterABEntity.add(AnchorBooksEntity(id = it.id, author = it.author,
                country = it.country, imageLink = it.imageLink, language = it.language,
                title = it.title, fav = true))
        }
        return converterABEntity
    }

    fun bookDetail(id: Int, author: String, country: String, imageLink: String, language: String,
                   link: String, pages: Int, title: String, year: Int, price: Int,
                   lastPrice: Int, delivery: Boolean): List<BookDetail> {
        val listBookDetail: MutableList<BookDetail> = mutableListOf()
        listBookDetail.add(
            BookDetail(
                id = id, author = author, country = country, imageLink = imageLink,
                language = language, link = link, pages = pages, title = title,
                year = year, price = price, lastPrice = lastPrice,
                delivery = delivery))

        return listBookDetail
    }
    suspend fun getAnchorBooksWhitCoroutines(){
        Log.d("REPOSITORY", "UTILIZANDO COROUTINES")
        try {
            val response = AnchorBooksRetrofitClient.retrofitInstance().fetchAnchorBooksList()
            when (response.isSuccessful){
                true -> response.body()?.let{
                    dao.insertAllAnchorBooksDao(converter(it.bookList))
                }
                false -> Log.d("ERROR", "${response.code()} : ${response.errorBody()}")
            }
        }
        catch (t:Throwable){
            Log.e("ERROR COROUTINE", t.message.toString())
        }
    }
    suspend fun getBookDetail(id:Int){
        try {
            val response = AnchorBooksRetrofitClient.retrofitInstance().fetchBookDetailEntity(id)
            when (response.isSuccessful){
                true -> response.body()?.let {
                    dao.insertAnchorBookDetail(bookDetail(id, it.author, it.country, it.imageLink,
                        it.language, it.link, it.pages, it.title, it.year, it.price, it.lastPrice,
                    it.delivery))
                }
                false -> Log.d("ERROR", "${response.code()} : ${response.errorBody()}")
            }
        }
        catch (t: Throwable) {
            Log.e("ERROR COROUTINE", t.message.toString())
        }
    }
    fun getAllAnchorBooksDaoDB(id: Int): LiveData<List<BookDetailEntity>> {
        return dao.getAnchorBookDetail(id)
    }
    suspend fun updateFav(anchorBooksEntity: AnchorBooksEntity) {
        dao.updateAnchorBooksEntity(anchorBooksEntity)
    }


}