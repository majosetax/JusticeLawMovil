package com.example.justicelawmovil.model

import com.google.gson.annotations.SerializedName

data class ForumQuestion(
    @SerializedName("id")
    val id: Int,

    @SerializedName("affair")
    val affair: String,

    @SerializedName("content")
    val content: String,

    @SerializedName("date_publication")
    val datePublication: String,

    @SerializedName("archive")
    val archive: String,

    @SerializedName("likes")
    val likes: Int,

    @SerializedName("dislikes")
    val dislikes: Int,

    @SerializedName("user_id")
    val userId: Int,

    @SerializedName("forum_category_id")
    val forumCategoryId: Int,

    @SerializedName("created_at")
    val createdAt: String,

    @SerializedName("updated_at")
    val updatedAt: String?
)
