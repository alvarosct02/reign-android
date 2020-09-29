package com.asct94.reigndemo.ui.posts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asct94.reigndemo.R
import com.asct94.reigndemo.models.Post
import kotlinx.android.synthetic.main.item_post.view.*

class PostsAdapter(
    private var objList: List<Post> = ArrayList()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val obj = objList[position]

        holder.itemView.tv_title.text = obj.fullTitle
        holder.itemView.tv_caption.text = "${obj.author} - ${obj.createTimeAgo}"

//        TODO("Not yet implemented")
    }

    override fun getItemCount() = objList.count()
}


class ViewHolder(view: View) : RecyclerView.ViewHolder(view)