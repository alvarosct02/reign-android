package com.asct94.reigndemo

import android.content.Context
import android.content.Intent
import com.asct94.reigndemo.data.source.api.PostService
import com.asct94.reigndemo.data.source.api.RequestManager

class Injector private constructor(
    val appContext: Context
) {

    private val requestManager: RequestManager by lazy {
        RequestManager("https://hn.algolia.com/api/v1/")
    }

    val postService: PostService by lazy {
        requestManager.getRetrofitClient().create(PostService::class.java)
    }


    companion object {
        lateinit var instance: Injector
            private set

        fun setup(appContext: Context) {
            instance = Injector(appContext)
        }
    }
}