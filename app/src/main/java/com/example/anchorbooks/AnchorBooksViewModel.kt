package com.example.anchorbooks

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class AnchorBooksViewModel (application: Application): AndroidViewModel(application){

    private val repository: AnchorBooksRepository
    val anchorBooksEntityLiveDataFromDB: LiveData<List<AnchorBooksEntity>>

    init {
        val dao = AnchorBooksDB.getDataBase(application).getAnchorBooksDAO()
        repository = AnchorBooksRepository(dao)
        viewModelScope.launch {
            repository.listAnchorBooks()
        }
    }
}