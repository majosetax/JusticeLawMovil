package com.example.justicelawmovil.model

import com.google.gson.annotations.SerializedName

data class UserProfileModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("cell_phone")
    val cell_phone: String? = null,
    @SerializedName("country_id")
    val country_id: Int? = null,
    @SerializedName("state_id")
    val state_id: Int? = null,
    @SerializedName("city_id")
    val city_id: Int? = null,
    @SerializedName("photo")
    val photo: String? = null,
    @SerializedName("user_id")
    val user_id: Int,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String
)
