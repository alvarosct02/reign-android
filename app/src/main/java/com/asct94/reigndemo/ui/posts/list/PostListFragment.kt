package com.asct94.reigndemo.ui.posts.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asct94.reigndemo.R
import com.asct94.reigndemo.models.Post
import com.asct94.reigndemo.ui.base.BaseFragment
import com.asct94.reigndemo.ui.posts.detail.PostDetailFragment
import kotlinx.android.synthetic.main.fragment_post_list.*

class PostListFragment : BaseFragment() {

    private val viewModel: PostListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_post_list, container, false)
    }

    override fun setupViews() {
        super.setupViews()

        swipe_refresh.setOnRefreshListener {
            viewModel.refresh()
        }

        rv_posts.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.HORIZONTAL))
            adapter = PostsAdapter(onItemClick = {
                openDetailView(it)
            })
        }

    }

    override fun setupVariables() {
        super.setupVariables()

        viewModel.postList.observe(viewLifecycleOwner, {
            (rv_posts.adapter as PostsAdapter).setItems(it)
        })
        viewModel.isLoading.observe(viewLifecycleOwner, {
            swipe_refresh.isRefreshing = it
        })

    }

    private fun openDetailView(post: Post) {
//      TODO: Validate if there's no internet
        PostDetailFragment.newInstance(findNavController(), post)
    }

    companion object {
        const val FIXED_QUERY = "android"
    }

}

