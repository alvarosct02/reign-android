package com.asct94.reigndemo.data.repository

import com.asct94.reigndemo.Injector
import com.asct94.reigndemo.data.source.api.PostService
import com.asct94.reigndemo.models.Post
import io.reactivex.Single


interface PostRepository {

    fun listPosts(query: String? = null): Single<List<Post>>

}


class PostRepositoryImpl(
    private val postService: PostService = Injector.instance.postService,
) : PostRepository {

    override fun listPosts(query: String?): Single<List<Post>> {
        return postService.listPosts(query).map { it.hits }
    }

}