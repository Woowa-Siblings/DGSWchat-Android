package kr.hs.dgsw.woowasiblings.dgswchat.presentation.feature.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kr.hs.dgsw.woowasiblings.dgswchat.R
import kr.hs.dgsw.woowasiblings.dgswchat.databinding.ItemCommentBinding
import kr.hs.dgsw.woowasiblings.dgswchat.databinding.LongQuestionItemBinding
import kr.hs.dgsw.woowasiblings.dgswchat.databinding.PostItemBinding
import kr.hs.dgsw.woowasiblings.dgswchat.domain.model.comment.Comment
import kr.hs.dgsw.woowasiblings.dgswchat.domain.model.post.Post

class PostAdapter(val onClick: (Post) -> Unit) : ListAdapter<List<Post>, PostAdapter.PostViewHolder>(PostDiffUtilCallback) {

    inner class PostViewHolder(private val binding: PostItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: List<Post>) {
            binding.apply {
                this.cv1.visibility = View.INVISIBLE
                this.cv2.visibility = View.INVISIBLE
                this.cv3.visibility = View.INVISIBLE
                this.cv4.visibility = View.INVISIBLE
                this.cv5.visibility = View.INVISIBLE

                if (item.size >= 1) {
                    this.cv1.visibility = View.VISIBLE
                    tvName1.text = item[0].userName
                    tvClass1.text = "${item[0].grade}학년 ${item[0].room}반 ${item[0].number}번"
                    tag1.text = "# " + if (item[0].tag == "Tech") "기술" else "학교"
                    tvTitle1.text = item[0].title
                    content1.text = item[0].content

                    cv1.setOnClickListener { onClick(item[0]) }
                }

                if (item.size >= 2) {
                    this.cv2.visibility = View.VISIBLE
                    tvName2.text = item[1].userName
                    tvClass2.text = "${item[1].grade}학년 ${item[1].room}반 ${item[1].number}번"
                    tag2.text = "# " + if (item[1].tag == "Tech") "기술" else "학교"
                    tvTitle2.text = item[1].title
                    content2.text = item[1].content

                    cv2.setOnClickListener { onClick(item[1]) }
                }

                if (item.size >= 3) {
                    this.cv3.visibility = View.VISIBLE
                    tvName3.text = item[2].userName
                    tvClass3.text = "${item[2].grade}학년 ${item[2].room}반 ${item[2].number}번"
                    tag3.text = "# " + if (item[2].tag == "Tech") "기술" else "학교"
                    tvTitle3.text = item[2].title
                    content3.text = item[2].content

                    cv3.setOnClickListener { onClick(item[2]) }
                }

                if (item.size >= 4) {
                    this.cv4.visibility = View.VISIBLE
                    tvName4.text = item[3].userName
                    tvClass4.text = "${item[3].grade}학년 ${item[3].room}반 ${item[3].number}번"
                    tag4.text = "# " + if (item[3].tag == "Tech") "기술" else "학교"
                    tvTitle4.text = item[3].title
                    content4.text = item[3].content

                    cv4.setOnClickListener { onClick(item[3]) }
                }

                if (item.size >= 5) {
                    this.cv5.visibility = View.VISIBLE
                    tvName5.text = item[4].userName
                    tvClass5.text = "${item[4].grade}학년 ${item[4].room}반 ${item[4].number}번"
                    tag5.text = "# " + if (item[4].tag == "Tech") "기술" else "학교"
                    tvTitle5.text = item[4].title
                    content5.text = item[4].content

                    cv5.setOnClickListener { onClick(item[4]) }
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.post_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object PostDiffUtilCallback : DiffUtil.ItemCallback<List<Post>>() {
        override fun areItemsTheSame(oldItem: List<Post>, newItem: List<Post>): Boolean = oldItem == newItem
        override fun areContentsTheSame(oldItem: List<Post>, newItem: List<Post>): Boolean = oldItem == newItem
    }

}
