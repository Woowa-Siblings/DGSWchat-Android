package kr.hs.dgsw.woowasiblings.dgswchat.presentation.feature.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kr.hs.dgsw.woowasiblings.dgswchat.R
import kr.hs.dgsw.woowasiblings.dgswchat.databinding.PostItemBinding
import kr.hs.dgsw.woowasiblings.dgswchat.domain.model.post.Post
import kr.hs.dgsw.woowasiblings.dgswchat.domain.model.post.PostList

class PostAdapter(val onClick: (Post) -> Unit) : ListAdapter<PostList, PostAdapter.PostViewHolder>(PostDiffUtilCallback) {

    inner class PostViewHolder(private val binding: PostItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PostList) {
            binding.apply {
                this.cv1.visibility = View.INVISIBLE
                this.cv2.visibility = View.INVISIBLE
                this.cv3.visibility = View.INVISIBLE
                this.cv4.visibility = View.INVISIBLE
                this.cv5.visibility = View.INVISIBLE

                item.data1?.let { post ->
                    this.cv1.visibility = View.VISIBLE
                    tvName1.text = post.userName
                    tvClass1.text = "${post.grade}학년 ${post.room}반 ${post.number}번"
                    tag1.text = "# " + if (post.tag == "Tech") "기술" else "학교"
                    tvTitle1.text = post.title
                    content1.text = post.content

                    cv1.setOnClickListener { onClick(post) }
                }

                item.data2?.let { post ->
                    this.cv2.visibility = View.VISIBLE
                    tvName2.text = post.userName
                    tvClass2.text = "${post.grade}학년 ${post.room}반 ${post.number}번"
                    tag2.text = "# " + if (post.tag == "Tech") "기술" else "학교"
                    tvTitle2.text = post.title
                    content2.text = post.content

                    cv2.setOnClickListener { onClick(post) }
                }

                item.data3?.let { post ->
                    this.cv3.visibility = View.VISIBLE
                    tvName3.text = post.userName
                    tvClass3.text = "${post.grade}학년 ${post.room}반 ${post.number}번"
                    tag3.text = "# " + if (post.tag == "Tech") "기술" else "학교"
                    tvTitle3.text = post.title
                    content3.text = post.content

                    cv3.setOnClickListener { onClick(post) }
                }

                item.data4?.let { post ->
                    this.cv4.visibility = View.VISIBLE
                    tvName4.text = post.userName
                    tvClass4.text = "${post.grade}학년 ${post.room}반 ${post.number}번"
                    tag4.text = "# " + if (post.tag == "Tech") "기술" else "학교"
                    tvTitle4.text = post.title
                    content4.text = post.content

                    cv4.setOnClickListener { onClick(post) }
                }

               item.data5?.let { post ->
                   this.cv5.visibility = View.VISIBLE
                   tvName5.text = post.userName
                   tvClass5.text = "${post.grade}학년 ${post.room}반 ${post.number}번"
                   tag5.text = "# " + if (post.tag == "Tech") "기술" else "학교"
                   tvTitle5.text = post.title
                   content5.text = post.content

                   cv5.setOnClickListener { onClick(post) }
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

    companion object PostDiffUtilCallback : DiffUtil.ItemCallback<PostList>() {
        override fun areItemsTheSame(oldItem: PostList, newItem: PostList): Boolean = oldItem == newItem
        override fun areContentsTheSame(oldItem: PostList, newItem: PostList): Boolean = oldItem == newItem
    }

}
