package com.asct94.reigndemo.ui.posts.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import com.asct94.reigndemo.R
import com.asct94.reigndemo.models.Post
import com.asct94.reigndemo.ui.base.BaseFragment
import com.google.gson.Gson


class PostDetailFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_post_list, container, false)
    }

    override fun setupViews() {
        super.setupViews()
    }

    companion object {

        private const val EXTRA_POST = "EXTRA_POST"

        fun newInstance(navController: NavController, post: Post) {
            navController.navigate(
                R.id.action_nav_post_detail,
                Bundle().apply {
                    putString(EXTRA_POST, Gson().toJson(post))
                })
        }
    }

}

