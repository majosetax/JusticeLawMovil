package com.example.justicelawmovil.model

import com.google.gson.annotations.SerializedName

data class NotificationModel(
    @SerializedName("id")
    var id: Int,

    @SerializedName("message")
    var message: String,

    @SerializedName("title")
    var title: String,

    @SerializedName("description")
    var description: String,

    @SerializedName("timestamp")
    var timestamp: String,

    @SerializedName("is_read")
    var isRead: Boolean,

    @SerializedName("user_id")
    var userId: Int,

    @SerializedName("created_at")
    var createdAt: String,

    @SerializedName("updated_at")
    var updatedAt: String?
)
