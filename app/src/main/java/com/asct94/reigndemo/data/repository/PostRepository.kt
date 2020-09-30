package com.asct94.reigndemo.data.repository

import com.asct94.reigndemo.Injector
import com.asct94.reigndemo.data.source.api.PostService
import com.asct94.reigndemo.data.source.local.PostDao
import com.asct94.reigndemo.models.Post
import io.reactivex.Completable
import io.reactivex.Single


interface PostRepository {

    fun listPosts(page: Int = 0): Single<List<Post>>

    fun listLocalPosts(): Single<List<Post>>

    fun deletePost(post:Post): Completable

}


class PostRepositoryImpl(
    private val postService: PostService = Injector.instance.postService,
    private val postDao: PostDao = Injector.instance.appDatabase.postDao(),
    ) : PostRepository {

    override fun listLocalPosts(): Single<List<Post>> {
        return postDao.getAll(PAGE_SIZE_FRONT)
    }

    override fun listPosts(page: Int): Single<List<Post>> {
        return postService.listPosts(query = "android", pageSize = PAGE_SIZE, page = page)
            .map {
//                This really impacts on performance but the API was returning null on storyIds
                it.hits.map { p -> Post.fromRaw(p) }
            }
            .map { postDao.insertAll(it) }
            .flatMap { postDao.getAll(limit = PAGE_SIZE_FRONT * (page + 1)) }
    }

    override fun deletePost(post:Post): Completable {
        return Completable.defer {
            postDao.deleteById(post.id)
            Completable.complete()
        }
    }

    companion object {
        const val PAGE_SIZE = 50
        const val PAGE_SIZE_FRONT = 30
    }
}