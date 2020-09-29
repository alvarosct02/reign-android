package com.asct94.reigndemo.models

import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName("title") val title: String?,
    @SerializedName("story_title") val storyTitle: String?,
    @SerializedName("author") val author: String?,
    @SerializedName("created_at") val createdAt: String,
) {

    val fullTitle: String get() {
        return title ?: storyTitle ?: "No Title Found"
    }

    val createTimeAgo: String get() {
        return createdAt
    }
}

