package kr.hs.dgsw.woowasiblings.dgswchat.presentation.feature.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import kr.hs.dgsw.woowasiblings.dgswchat.R
import kr.hs.dgsw.woowasiblings.dgswchat.databinding.ChatItemAuthorBinding
import kr.hs.dgsw.woowasiblings.dgswchat.databinding.ChatItemBinding
import kr.hs.dgsw.woowasiblings.dgswchat.domain.model.Chat
import java.time.LocalDateTime
import java.time.LocalTime

class ChatAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val chats = mutableListOf<Chat>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : RecyclerView.ViewHolder {
        return when(viewType) {
            0 -> {
                ChatViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.chat_item,
                        parent,
                        false
                    )
                )
            }
            else -> {
                AuthorViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.chat_item_author,
                        parent,
                        false
                    )
                )
            }
        }
    }
    override fun getItemCount(): Int = chats.size

    override fun getItemViewType(position: Int): Int {
        return chats[position].isAuthor
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(chats[position].isAuthor) {
            0 -> {
                (holder as ChatViewHolder).bind(chats[position])
                holder.setIsRecyclable(false)
            }
            else -> {
                (holder as AuthorViewHolder).bind(chats[position])
                holder.setIsRecyclable(false)
            }
        }
    }

    inner class ChatViewHolder(private val binding: ChatItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Chat) {
            binding.tvMessage.text = item.message
            binding.tvTime.text = LocalTime.now().toString()
        }
    }
    inner class AuthorViewHolder(private val binding: ChatItemAuthorBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Chat) {
            binding.tvMessage.text = item.message
            binding.tvTime.text = LocalDateTime.now().minute.toString()
        }
    }

}