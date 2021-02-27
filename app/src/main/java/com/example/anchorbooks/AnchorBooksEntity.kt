package com.example.anchorbooks

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "anchorBooks_table")
data class AnchorBooksEntity(@SerializedName ("id")
                             @PrimaryKey val id: Int,
                             @SerializedName ("author")
                             val author: String,
                             @SerializedName ("country")
                             val  country: String,
                             @SerializedName ("imageLink")
                             val imageLink: String,
                             @SerializedName ("language")
                             var language: String,
                             @SerializedName ("title")
                             val title: String
)
