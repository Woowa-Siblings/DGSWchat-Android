package kr.hs.dgsw.woowasiblings.dgswchat.presentation.feature.home

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.woowasiblings.dgswchat.R
import kr.hs.dgsw.woowasiblings.dgswchat.databinding.FragmentHomeBinding
import kr.hs.dgsw.woowasiblings.dgswchat.domain.model.post.Post
import kr.hs.dgsw.woowasiblings.dgswchat.presentation.base.BaseFragment
import kr.hs.dgsw.woowasiblings.dgswchat.presentation.feature.adapter.PostAdapter
import kr.hs.dgsw.woowasiblings.dgswchat.presentation.feature.chat.ChatFragment
import kr.hs.dgsw.woowasiblings.dgswchat.presentation.utils.extension.repeatOnStarted

@AndroidEntryPoint
class HomeFragment: BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home) {

    override val viewModel: HomeViewModel by viewModels()

    override fun start() {
        with(mViewModel) {
            repeatOnStarted { eventFlow.collect { event -> handleEvent(event) } }
            getPost()

            binding.apply {
                btnSogu.setOnClickListener {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment, ChatFragment())
                        .addToBackStack(null)
                        .commit()
                }
//
//                btnAdd.setOnClickListener {
//                    requireActivity().supportFragmentManager.beginTransaction()
//                        .replace(R.id.fragment, AddFragment())
//                        .commit()
//                }
            }
        }
    }

    private fun initAdapter(posts: List<Post>) {
        val postAdapter = PostAdapter()
        postAdapter.submitList(posts)
        binding.rvPost.adapter = postAdapter
    }

    private fun handleEvent(event: HomeViewModel.Event): Any =
        when (event) {
            is HomeViewModel.Event.SuccessPost -> initAdapter(event.posts)
            is HomeViewModel.Event.UnkownException -> shortToast("알 수 없는 오류가 발생했습니다.")
        }
}