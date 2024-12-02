package com.example.justicelawmovil.model

import com.google.gson.annotations.SerializedName

data class CountryModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("code")
    val code: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("phonecode")
    val phonecode: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String
)
