package com.asct94.reigndemo.models

import android.text.format.DateUtils
import com.asct94.reigndemo.utils.toCalendar
import com.google.gson.annotations.SerializedName
import java.util.*

data class Post(
    @SerializedName("title") val title: String?,
    @SerializedName("story_title") val storyTitle: String?,
    @SerializedName("author") val author: String?,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("story_url") val storyUrl: String,
) {

    val fullTitle: String
        get() = title ?: storyTitle ?: "-"

    val createTimeAgo: String
        get() = DateUtils.getRelativeTimeSpanString(
            createdAt.toCalendar().timeInMillis,
            Calendar.getInstance().timeInMillis,
            DateUtils.MINUTE_IN_MILLIS
        ).toString()

}

