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

    private fun fetchLocalList() {
        disposable.add(
            postRepository.listLocalPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    postList.value = it
                    isLoading.value = false
                    fetchList()
                }, {
                    Log.e("ASCT", it.message)
                    isLoading.value = false
                    fetchList()
                })
        )
    }

    private fun fetchList(showLoading: Boolean = true) {

        if (showLoading) isLoading.value = true

        disposable.add(
            postRepository.listPosts(query = PostListFragment.FIXED_QUERY)
                .delay(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    postList.value = it
                    isLoading.value = false
                }, {
                    Log.e("ASCT", it.message)
                    isLoading.value = false
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

    fun refresh(showLoading: Boolean = false) {
        fetchList(showLoading)
    }


}