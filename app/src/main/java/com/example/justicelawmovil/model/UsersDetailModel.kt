package com.example.justicelawmovil.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UsersDetailModel(
    @SerializedName("name") val name:String,
    @SerializedName("last_name") val last_name: String,
    @SerializedName("document_number") val document_number: String,
    @SerializedName("email") val email: String
) : Parcelable

