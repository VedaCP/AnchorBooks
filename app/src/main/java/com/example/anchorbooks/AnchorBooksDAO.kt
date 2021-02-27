package com.example.anchorbooks

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AnchorBooksDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllAnchorBooksDao(list: List<AnchorBooksEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnchorBookDetail(list: List<AnchorBookDetail>)

    @Query("SELECT * FROM anchorBooks_table")
    fun getAllAnchorBooksDaoDB(): LiveData<List<AnchorBooksEntity>>

    @Query("SELECT * FROM detail_table WHERE id = :id")
    fun getAnchorBooksById(id: Int) : LiveData<List<AnchorBookDetail>>



}