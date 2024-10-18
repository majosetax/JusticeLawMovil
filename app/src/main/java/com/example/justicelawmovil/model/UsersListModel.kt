package com.example.justicelawmovil.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
@Parcelize
data class UsersListModel(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<UserListItem>
): Parcelable

@Parcelize
data class UserListItem(
@SerializedName("name") val name: String,
    @SerializedName("url") val url: String
): Parcelable
