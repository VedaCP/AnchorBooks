package com.example.anchorbooks

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class AnchorBooksViewModel (application: Application): AndroidViewModel(application){

    private val repository: AnchorBooksRepository
    val aBooksLiveDataFromDB: LiveData<List<AnchorBooksEntity>>

    init {
        val dao = AnchorBooksDB.getDataBase(application).getAnchorBooksDAO()
        repository = AnchorBooksRepository(dao)
        viewModelScope.launch {
            repository.getAnchorBooksWhitCoroutines()
        }
        aBooksLiveDataFromDB = repository.listABooks
    }
    fun getAllAnchorBooksDaoDB(): LiveData<List<AnchorBooksEntity>> = repository.listABooks

    fun getAnchorBookDetail(id:Int) = viewModelScope.launch {
        repository.getBookDetail(id)
    }
}