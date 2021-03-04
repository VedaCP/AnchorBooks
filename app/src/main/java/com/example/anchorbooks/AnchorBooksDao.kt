package com.example.anchorbooks

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AnchorBooksDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllAnchorBooksDao(list: List<AnchorBooksEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnchorBookDetail(list: List<BookDetail>)

    @Query("SELECT * FROM anchorBooks_table")
    fun getAllAnchorBooksDaoDB(): LiveData<List<AnchorBooksEntity>>

    @Query("SELECT * FROM detail_table WHERE id = :id")
    fun getAnchorBookDetail(id: Int) : LiveData<List<BookDetailEntity>>

    @Update
    suspend fun updateAnchorBooksEntity(anchorBooksEntity: AnchorBooksEntity)



}