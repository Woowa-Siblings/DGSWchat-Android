package kr.hs.dgsw.woowasiblings.dgswchat.presentation.feature.detail

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.woowasiblings.dgswchat.R
import kr.hs.dgsw.woowasiblings.dgswchat.databinding.FragmentDetailBinding
import kr.hs.dgsw.woowasiblings.dgswchat.domain.model.comment.Comment
import kr.hs.dgsw.woowasiblings.dgswchat.domain.model.comment.CommentDto
import kr.hs.dgsw.woowasiblings.dgswchat.presentation.base.BaseFragment
import kr.hs.dgsw.woowasiblings.dgswchat.presentation.feature.adapter.CommentAdapter
import kr.hs.dgsw.woowasiblings.dgswchat.presentation.utils.extension.repeatOnStarted

@AndroidEntryPoint
class DetailFragment :
    BaseFragment<FragmentDetailBinding, DetailViewModel>(R.layout.fragment_detail) {

    override val viewModel: DetailViewModel by viewModels()
    override fun start() {
        val postId = arguments?.getInt("postId")!!

        with(mViewModel) {
            repeatOnStarted { eventFlow.collect { event -> handleEvent(event) } }
            getPostByPostId(postId)
            getComment(postId)
            binding.apply {
                btnSend.setOnClickListener {
                    submitComment(
                        CommentDto(
                            postId,
                            etMessage.text.toString()
                        )
                    )
                    etMessage.text = null
                }
                btnBack.setOnClickListener {
                    requireActivity().supportFragmentManager.popBackStack()
                }
            }
        }

    }

    private fun initAdapter(comment: List<Comment>) {
        val commentAdapter = CommentAdapter()
        commentAdapter.submitList(comment)
        binding.rvComment.adapter = commentAdapter
    }

    private fun handleEvent(event: DetailViewModel.Event): Any =
        when (event) {
            is DetailViewModel.Event.SuccessPostByPostId -> binding.apply {
                tvName.text = event.post.userName
                tvClass.text = "${event.post.grade}?????? ${event.post.room}??? ${event.post.number}???"
                tvTitle.text = event.post.title
                content.text = event.post.content
                tag.text = "# " + if (event.post.tag == "Tech") "??????" else "??????"
            }

            is DetailViewModel.Event.SuccessSubmit -> mViewModel.getComment(arguments?.getInt("postId")!!)
            is DetailViewModel.Event.SuccessComment -> initAdapter(event.comments)
            is DetailViewModel.Event.UnkownException -> shortToast("??? ??? ?????? ????????? ??????????????????.")
        }
}