package com.example.anchorbooks

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database (entities = [AnchorBooksEntity::class, BookDetailEntity::class], version = 1)

abstract class AnchorBooksDB: RoomDatabase() {

    abstract fun getAnchorBooksDAO(): AnchorBooksDAO

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
                AnchorBooksDB::class.java, "AnchorBooksDB").build()
                INSTANCE = instance
                return instance
            }
        }
    }

}