package com.example.justicelawmovil.model

import com.google.gson.annotations.SerializedName

data class UserModel (
    @SerializedName("id")
    var id:Int,
    @SerializedName("name")
    var name:String,
    @SerializedName("last_name")
    var last_name:String,
    @SerializedName("type_document_id")
    var type_document_id:Int,
    @SerializedName("document_number")
    var document_number:String,
    @SerializedName("email")
    var email:String,
    @SerializedName("email_verified_at")
    var email_verified_at: String?,
    @SerializedName("created_at")
    var created_at: String,
    @SerializedName("updated_at")
    var updated_at: String
)