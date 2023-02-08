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
                tvClass.text = "${event.post.grade}학년 ${event.post.room}반 ${event.post.number}번"
                tvTitle.text = event.post.title
                content.text = event.post.content
                tag.text = "# " + if (event.post.tag == "Tech") "기술" else "학교"
            }

            is DetailViewModel.Event.SuccessSubmit -> mViewModel.getComment(arguments?.getInt("postId")!!)
            is DetailViewModel.Event.SuccessComment -> initAdapter(event.comments)
            is DetailViewModel.Event.UnkownException -> shortToast("알 수 없는 오류가 발생했습니다.")
        }
}