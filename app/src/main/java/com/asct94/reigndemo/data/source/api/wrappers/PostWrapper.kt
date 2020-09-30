package com.asct94.reigndemo.data.source.api.wrappers

import com.google.gson.annotations.SerializedName


data class PostWrapper(
    val hits: List<RawPost>,
)

data class RawPost(
    @SerializedName("story_id") var id: Long?,
    @SerializedName("title") val title: String?,
    @SerializedName("story_title") val storyTitle: String?,
    @SerializedName("author") val author: String?,
    @SerializedName("created_at_i") val createdAt: String?,
    @SerializedName("story_url") val storyUrl: String?,
    @SerializedName("_tags") val tag: List<String>?,
)