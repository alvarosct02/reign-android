package com.asct94.reigndemo.models

import android.text.format.DateUtils
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.asct94.reigndemo.data.source.api.wrappers.RawPost
import com.asct94.reigndemo.utils.toCalendar
import java.util.*

@Entity
class Post(
    @PrimaryKey var id: Long,
    val title: String?,
    val storyTitle: String?,
    val author: String?,
    val createdAt: String?,
    val storyUrl: String?,
) {

    var deleted: Boolean = false

    val fullTitle: String
        get() = title ?: storyTitle ?: "-"

    val createTimeAgo: String
        get() {
            if (createdAt == null) return "???"
            return DateUtils.getRelativeTimeSpanString(
                createdAt.toLong() * 1000,
                Calendar.getInstance().timeInMillis,
                DateUtils.MINUTE_IN_MILLIS
            ).toString()
        }

    companion object {
        fun fromRaw(rawPost: RawPost): Post {

            val storyIdTag = rawPost.tag?.getOrNull(2)
            val storyId = storyIdTag?.substring("story_".length)?.toLongOrNull()
            return Post(
                id = storyId ?: rawPost.id ?: -1,
                title = rawPost.title,
                storyTitle = rawPost.storyTitle,
                author = rawPost.author,
                createdAt = rawPost.createdAt,
                storyUrl = rawPost.storyUrl,
            )
        }
    }
}



