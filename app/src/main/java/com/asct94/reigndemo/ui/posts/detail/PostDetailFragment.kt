package com.asct94.reigndemo.ui.posts.detail

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.asct94.reigndemo.R
import com.asct94.reigndemo.models.Post
import com.asct94.reigndemo.ui.base.BaseFragment
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_post_detail.*


class PostDetailFragment : BaseFragment() {

    private var postUrl: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_post_detail, container, false)
    }

    override fun setupVariables() {
        super.setupVariables()
        val post = Gson().fromJson(requireArguments().getString(EXTRA_POST), Post::class.java)
        postUrl = post.storyUrl
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun setupViews() {
        super.setupViews()

        wv_content.settings.javaScriptEnabled = true
        wv_content.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                pb_loading?.isVisible = false
            }
        }
        postUrl.takeUnless { it.isNullOrEmpty() }?.also { wv_content.loadUrl(it) } ?: showError()

    }

    private fun showError() {
        wv_content?.isVisible = false
        pb_loading?.isVisible = false

        val alertDialog = AlertDialog.Builder(requireContext())
            .setTitle(R.string.app_name)
            .setMessage(R.string.message_web_detail_error)
            .setIcon(R.drawable.ic_logo)
            .setPositiveButton(R.string.label_ok) { _, _ ->
                findNavController().navigateUp()
            }
            .create()

        alertDialog.setCancelable(false)
        alertDialog.show()
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

