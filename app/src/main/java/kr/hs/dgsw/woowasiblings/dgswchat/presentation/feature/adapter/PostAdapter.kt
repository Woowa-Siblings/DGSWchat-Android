package kr.hs.dgsw.woowasiblings.dgswchat.presentation.feature.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kr.hs.dgsw.woowasiblings.dgswchat.R
import kr.hs.dgsw.woowasiblings.dgswchat.databinding.LongQuestionItemBinding
import kr.hs.dgsw.woowasiblings.dgswchat.domain.model.post.Post

class PostAdapter : ListAdapter<Post, PostAdapter.PostViewHolder>(PostDiffUtilCallback) {

    inner class PostViewHolder(private val binding: LongQuestionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Post) {
            binding.apply {
                tvTitle.text = item.title
                tvName.text = item.userName
                tvClass.text = "${item.grade}학년 ${item.room}반 ${item.number}번"
                content.text = item.content
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.long_question_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object PostDiffUtilCallback : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean = oldItem == newItem
        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean = oldItem == newItem
    }

}
