package com.asct94.reigndemo.ui.posts

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asct94.reigndemo.R
import com.asct94.reigndemo.data.repository.PostRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_posts.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.HORIZONTAL))
            adapter = PostsAdapter()
        }

        fetchList()
    }


    private fun fetchList(showLoading: Boolean = true) {

//        if (showLoading) isLoading.value = true

//        val query = queryTerm.value.takeUnless { it.isNullOrEmpty() }


//        disposable.add(
        PostRepositoryImpl().listPosts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                rv_posts.adapter = PostsAdapter(objList = it)
                Log.e("ASCT", it.count().toString())
//                isLoading.value = false
//                studyList.value = it
            }, {
//                isLoading.value = false
//                setError(it.message)
                Log.e("ASCT", it.message)
            })
//        )
    }

}