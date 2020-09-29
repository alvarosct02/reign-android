package com.asct94.reigndemo.ui.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asct94.reigndemo.R
import com.asct94.reigndemo.models.Post

class PostsAdapter(
    private var objList: List<Post> = ArrayList(),
    private var onItemClick: ((Post) -> Unit)? = null
) : RecyclerView.Adapter<PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        ).apply {
            itemView.setOnClickListener {
                onItemClick?.invoke(objList.get(adapterPosition))
            }
        }
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val obj = objList[position]
        holder.bind(obj)
    }

    override fun getItemCount() = objList.count()
}
