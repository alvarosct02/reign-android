package com.asct94.reigndemo.models

import android.text.format.DateUtils
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.asct94.reigndemo.utils.toCalendar
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity
class Post(
    @PrimaryKey
    @SerializedName("story_id") var id: Long,
    @SerializedName("title") val title: String?,
    @SerializedName("story_title") val storyTitle: String?,
    @SerializedName("author") val author: String?,
    @SerializedName("created_at") val createdAt: String?,
    @SerializedName("story_url") val storyUrl: String?,
) {
    var deleted: Boolean = false

    val fullTitle: String
        get() = title ?: storyTitle ?: "-"

    val createTimeAgo: String
        get() {
            if (createdAt == null) return "???"
            return DateUtils.getRelativeTimeSpanString(
                createdAt.toCalendar().timeInMillis,
                Calendar.getInstance().timeInMillis,
                DateUtils.MINUTE_IN_MILLIS
            ).toString()
        }

}

