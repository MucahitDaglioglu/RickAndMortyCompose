package com.mucahitdaglioglu.rickandmorty.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.mucahitdaglioglu.rickandmorty.data.model.Origin

@Entity(tableName = "favorites_table")
data class CharacterDetail(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val image: String?,
    val name: String?,
    val gender: String?,
    val status: String?,
    val locationName: String?,
    val species: String?,
    val originName: String?
)