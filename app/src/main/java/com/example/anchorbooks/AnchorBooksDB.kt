package com.example.anchorbooks

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database (entities = [AnchorBooksEntity::class, BookDetailEntity::class], version = 1)

abstract class AnchorBooksDB: RoomDatabase() {

    abstract fun getAnchorBooksDAO(): AnchorBooksDao

    companion object{
        @Volatile
        private var INSTANCE: AnchorBooksDB? = null
        fun getDataBase(context: Context): AnchorBooksDB{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,
                AnchorBooksDB::class.java, "booksDB").build()
                INSTANCE = instance
                return instance
            }
        }
    }

}