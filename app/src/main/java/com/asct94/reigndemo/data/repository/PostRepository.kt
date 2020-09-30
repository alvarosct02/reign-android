package com.asct94.reigndemo.data.repository

import com.asct94.reigndemo.Injector
import com.asct94.reigndemo.data.source.api.PostService
import com.asct94.reigndemo.data.source.local.PostDao
import com.asct94.reigndemo.models.Post
import io.reactivex.Completable
import io.reactivex.Single


interface PostRepository {

    fun listPosts(query: String? = null): Single<List<Post>>

    fun listLocalPosts(): Single<List<Post>>

    fun deletePost(post:Post): Completable

}


class PostRepositoryImpl(
    private val postService: PostService = Injector.instance.postService,
    private val postDao: PostDao = Injector.instance.appDatabase.postDao(),
    ) : PostRepository {

    override fun listLocalPosts(): Single<List<Post>> {
        return postDao.getAll()
    }

    override fun listPosts(query: String?): Single<List<Post>> {
        return postService.listPosts(query)
            .map { it.hits }
            .map { postDao.insertAll(it) }
            .flatMap { postDao.getAll() }
    }

    override fun deletePost(post:Post): Completable {
        return Completable.defer {
            postDao.deleteById(post.id)
            Completable.complete()
        }
    }
}