package com.example.justicelawmovil.model

import com.google.gson.annotations.SerializedName

data class TypeDocumentModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("code")
    val code: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("created_at")
val createdAt: String,
@SerializedName("updated_at")
val updatedAt: String
)
