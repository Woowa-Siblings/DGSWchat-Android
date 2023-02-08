package kr.hs.dgsw.woowasiblings.dgswchat.presentation.feature.user

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.woowasiblings.dgswchat.App
import kr.hs.dgsw.woowasiblings.dgswchat.R
import kr.hs.dgsw.woowasiblings.dgswchat.databinding.FragmentUserBinding
import kr.hs.dgsw.woowasiblings.dgswchat.domain.model.post.Post
import kr.hs.dgsw.woowasiblings.dgswchat.domain.model.post.PostList
import kr.hs.dgsw.woowasiblings.dgswchat.presentation.base.BaseFragment
import kr.hs.dgsw.woowasiblings.dgswchat.presentation.feature.adapter.PostAdapter
import kr.hs.dgsw.woowasiblings.dgswchat.presentation.feature.detail.DetailFragment
import kr.hs.dgsw.woowasiblings.dgswchat.presentation.feature.login.LoginFragment
import kr.hs.dgsw.woowasiblings.dgswchat.presentation.utils.extension.repeatOnStarted

@AndroidEntryPoint
class UserFragment : BaseFragment<FragmentUserBinding, UserViewModel>(R.layout.fragment_user) {

    override val viewModel: UserViewModel by viewModels()

    override fun start() {
        with(mViewModel) {
            getUser()
            repeatOnStarted { eventFlow.collect { event -> handleEvent(event) } }
                binding.apply {
                    btnBack.setOnClickListener {
                        requireActivity().supportFragmentManager.popBackStack()
                    }
                    btnLogout.setOnClickListener {
                        App.prefs.deleteToken()
                        requireActivity().supportFragmentManager.apply {
                            popBackStack()
                        }.beginTransaction()
                            .replace(R.id.fragment, LoginFragment())
                            .commit()
                    }
                }
            }
    }

    private fun initAdapter(posts: List<Post>) {
        val postList: MutableList<PostList> = mutableListOf()

        for (i in 0 until posts.size / 5) {
            postList.add(
                PostList(
                    posts[i * 5],
                    posts[i * 5 + 1],
                    posts[i * 5 + 2],
                    posts[i * 5 + 3],
                    posts[i * 5 + 4]
                )
            )
        }
        val idx = (posts.size / 5) * 5
        when (posts.size % 5) {
            1 -> postList.add(PostList(posts[idx]))
            2 -> postList.add(PostList(posts[idx], posts[idx + 1]))
            3 -> postList.add(PostList(posts[idx], posts[idx + 1], posts[idx + 2]))
            4 -> postList.add(PostList(posts[idx], posts[idx + 1], posts[idx + 2], posts[idx + 3]))
        }

        val postAdapter = PostAdapter {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment, DetailFragment().apply {
                    arguments = Bundle().apply { putInt("postId", it.postId) }
                }).addToBackStack(null)
                .commit()
        }

        postAdapter.submitList(postList)
        binding.rvPost.adapter = postAdapter
    }

    private fun handleEvent(event: UserViewModel.Event): Any =
        when (event) {
            is UserViewModel.Event.SuccessUser -> {
                val user = event.user.user
                binding.apply {
                    tvId.text = user.userId
                    tvClass.text = "${user.grade}학년 ${user.room}반 ${user.number}번"
                    tvName.text = user.nickname
                }
                initAdapter(event.user.post)
            }
            is UserViewModel.Event.UnkownException -> shortToast("알 수 없는 오류가 발생했습니다.")
        }

}