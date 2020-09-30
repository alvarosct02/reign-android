package com.asct94.reigndemo.ui.posts.list

import android.graphics.Canvas
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asct94.reigndemo.R
import com.asct94.reigndemo.models.Post
import com.asct94.reigndemo.ui.base.BaseFragment
import com.asct94.reigndemo.ui.posts.detail.PostDetailFragment
import kotlinx.android.synthetic.main.fragment_post_list.*

class PostListFragment : BaseFragment() {

    private val viewModel: PostListViewModel by activityViewModels()

    private lateinit var postsAdapter: PostsAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    private lateinit var colorDrawableBackground: ColorDrawable
    private lateinit var deleteIcon: Drawable


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_post_list, container, false)
    }

    override fun setupVariables() {
        super.setupVariables()

        viewModel.postList.observe(viewLifecycleOwner, {
            postsAdapter.setItems(it)
        })
        viewModel.isLoading.observe(viewLifecycleOwner, {
            swipe_refresh.isRefreshing = it
        })
        viewModel.isLoadingMore.observe(viewLifecycleOwner, {
            pb_loading_more.isVisible = it
        })

        colorDrawableBackground =
            ColorDrawable(ContextCompat.getColor(requireContext(), R.color.red))
        deleteIcon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_delete_sweep)!!

        postsAdapter = PostsAdapter(onItemClick = {
            openDetailView(it)
        }, onItemDeleted = {
            viewModel.onPostRemoved(it)
        })

        linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

    }


    override fun setupViews() {
        super.setupViews()

        swipe_refresh.setOnRefreshListener {
            viewModel.refresh()
        }

        rv_posts.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
            adapter = postsAdapter
            ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(this)
            addOnScrollListener(scrollListener)
        }

    }

    private val itemTouchHelperCallback =
        object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.START) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                postsAdapter.removeItem(viewHolder.adapterPosition)
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                val itemView = viewHolder.itemView
                val iconMarginVertical =
                    (viewHolder.itemView.height - deleteIcon.intrinsicHeight) / 2

                c.save()
                colorDrawableBackground.setBounds(
                    itemView.left,
                    itemView.top,
                    itemView.right,
                    itemView.bottom
                )
                deleteIcon.setBounds(
                    itemView.right - iconMarginVertical - deleteIcon.intrinsicWidth,
                    itemView.top + iconMarginVertical,
                    itemView.right - iconMarginVertical,
                    itemView.bottom - iconMarginVertical
                )
                c.clipRect(
                    itemView.right + dX.toInt(),
                    itemView.top,
                    itemView.right,
                    itemView.bottom
                )
                colorDrawableBackground.draw(c)
                deleteIcon.draw(c)
                c.restore()

                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
            }
        }

    private val scrollListener: RecyclerView.OnScrollListener =
        object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (viewModel.isLoading.value == true || viewModel.isLoadingMore.value == true) return
                val visibleItemCount: Int = linearLayoutManager.childCount
                val totalItemCount: Int = linearLayoutManager.itemCount
                val pastVisibleItems: Int = linearLayoutManager.findFirstVisibleItemPosition()
                if (pastVisibleItems + visibleItemCount >= totalItemCount) {
                    viewModel.loadMorePosts()
                }
            }
        }

    private fun openDetailView(post: Post) {
//      TODO: Validate if there's no internet
        PostDetailFragment.newInstance(findNavController(), post)
    }


}

