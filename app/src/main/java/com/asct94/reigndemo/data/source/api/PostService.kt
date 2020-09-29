package com.asct94.reigndemo.data.source.api

import com.asct94.reigndemo.data.source.api.wrappers.PostWrapper
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface PostService {

    @GET("search_by_date")
    fun listPosts(
        @Query("query") query: String?
    ): Single<PostWrapper>

}
