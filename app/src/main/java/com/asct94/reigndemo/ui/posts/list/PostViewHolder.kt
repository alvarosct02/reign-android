package com.asct94.reigndemo.ui.posts.list

import android.R
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.asct94.reigndemo.models.Post
import com.daimajia.swipe.SwipeLayout
import kotlinx.android.synthetic.main.item_post.view.*


class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(post: Post) {
        itemView.tv_title.text = post.fullTitle
        itemView.tv_caption.text = "${post.author} - ${post.createTimeAgo}"
    }

}