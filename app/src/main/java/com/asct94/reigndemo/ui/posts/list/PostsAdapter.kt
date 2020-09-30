package com.asct94.reigndemo.ui.posts.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asct94.reigndemo.R
import com.asct94.reigndemo.models.Post
import com.daimajia.swipe.SwipeLayout
import kotlinx.android.synthetic.main.item_post.view.*

class PostsAdapter(
    private var objList: MutableList<Post> = mutableListOf(),
    private var onItemClick: ((Post) -> Unit)? = null,
    private var onItemDeleted: ((Post) -> Unit)? = null
) : RecyclerView.Adapter<PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        ).apply {
            itemView.setOnClickListener {
                onItemClick?.invoke(objList[adapterPosition])
            }
        }
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val obj = objList[position]
        holder.bind(obj)
    }

    override fun getItemCount() = objList.count()

    fun setItems(newObjList: List<Post>) {
        this.objList = newObjList.toMutableList()
        notifyDataSetChanged()
    }

    fun removeItem(removedPosition: Int) {
        val obj = objList.removeAt(removedPosition)
        notifyItemRemoved(removedPosition)
        onItemDeleted?.invoke(obj)
//
//        Snackbar.make(viewHolder.itemView, "$removedItem removed", Snackbar.LENGTH_LONG)
//            .setAction("UNDO") {
//                dataset.add(removedPosition, removedItem)
//                notifyItemInserted(removedPosition)
//
//            }.show()
    }
}
