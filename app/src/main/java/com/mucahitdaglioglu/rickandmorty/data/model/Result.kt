package com.mucahitdaglioglu.rickandmorty.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Result(
    @SerializedName("created")
    val created: String?,
    @SerializedName("episode")
    val episode: List<String?>?,
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("location")
    val location: Location?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("origin")
    val origin: Origin?,
    @SerializedName("species")
    val species: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("url")
    val url: String?
): Parcelable