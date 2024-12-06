package com.example.justicelawmovil.model

import com.google.gson.annotations.SerializedName

data class CityModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("state_id")
    val state_id: Int,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String
)