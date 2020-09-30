package com.asct94.reigndemo.ui.posts.list

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.asct94.reigndemo.data.repository.PostRepository
import com.asct94.reigndemo.data.repository.PostRepositoryImpl
import com.asct94.reigndemo.models.Post
import com.asct94.reigndemo.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit


class PostListViewModel(
    private val postRepository: PostRepository = PostRepositoryImpl()
) : BaseViewModel() {

    val postList: MutableLiveData<List<Post>> by lazy {
        MutableLiveData<List<Post>>().also {
            fetchLocalList()
        }
    }

    val isLoadingMore = MutableLiveData(false)

    private val currentPage = MutableLiveData(0)
    private val safePage get() = currentPage.value ?: 0

    private fun fetchLocalList() {
        disposable.add(
            postRepository.listLocalPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    postList.value = it
                    isLoading.value = false
                    refresh()
                }, {
                    Log.e("ASCT", it.message)
                    isLoading.value = false
                    refresh()
                })
        )
    }

    private fun fetchList(page: Int, showRefreshing: Boolean, showLoadingMore: Boolean) {

        if (showRefreshing) isLoading.value = true
        if (showLoadingMore) isLoadingMore.value = true

        disposable.add(
            postRepository.listPosts(page = page)
                .delay(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    postList.value = it
                    isLoading.value = false
                    isLoadingMore.value = false
                    currentPage.value = page
                }, {
                    Log.e("ASCT", it.message)
                    isLoading.value = false
                    isLoadingMore.value = false
                })
        )
    }

    fun onPostRemoved(post: Post) {
        disposable.add(
            postRepository.deletePost(post)
                .subscribeOn(Schedulers.io())
                .subscribe()
        )
    }

    fun refresh() {
        fetchList(page = 0, showRefreshing = true, showLoadingMore = false)
    }

    fun loadMorePosts() {
        fetchList(page = safePage + 1, showRefreshing = false, showLoadingMore = true)

//        fetchList()
    }


}