package com.asct94.reigndemo.data.source.api.wrappers

import com.asct94.reigndemo.models.Post


data class PostWrapper(
    val hits: List<Post>,
)

