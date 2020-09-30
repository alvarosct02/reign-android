package com.asct94.reigndemo

import android.content.Context
import android.content.Intent
import com.asct94.reigndemo.data.source.api.PostService
import com.asct94.reigndemo.data.source.api.RequestManager
import com.asct94.reigndemo.data.source.local.AppDatabase

class Injector private constructor(
    private val appContext: Context
) {

    val appDatabase: AppDatabase by lazy {
        AppDatabase.init(appContext)
        AppDatabase.getInstance()
    }

    private val requestManager: RequestManager by lazy {
        RequestManager(BuildConfig.BASE_URL)
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