package kr.hs.dgsw.woowasiblings.dgswchat.presentation.feature.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kr.hs.dgsw.woowasiblings.dgswchat.R
import kr.hs.dgsw.woowasiblings.dgswchat.databinding.NotificationItemBinding
import kr.hs.dgsw.woowasiblings.dgswchat.domain.model.notice.Notice

class NoticeAdapter : ListAdapter<Notice, NoticeAdapter.NoticeViewHolder>(NoticeDiffUtilCallback) {

    inner class NoticeViewHolder(private val binding: NotificationItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Notice) {
            binding.apply {

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeViewHolder {
        return NoticeViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.notification_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NoticeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object NoticeDiffUtilCallback : DiffUtil.ItemCallback<Notice>() {
        override fun areItemsTheSame(oldItem: Notice, newItem: Notice): Boolean = oldItem == newItem
        override fun areContentsTheSame(oldItem: Notice, newItem: Notice): Boolean = oldItem == newItem
    }

}