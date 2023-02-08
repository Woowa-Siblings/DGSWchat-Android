package kr.hs.dgsw.woowasiblings.dgswchat.presentation.feature.add

import androidx.fragment.app.viewModels
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.woowasiblings.dgswchat.R
import kr.hs.dgsw.woowasiblings.dgswchat.databinding.FragmentAddBinding
import kr.hs.dgsw.woowasiblings.dgswchat.presentation.base.BaseFragment
import kr.hs.dgsw.woowasiblings.dgswchat.presentation.feature.chat.ChatViewModel
import kr.hs.dgsw.woowasiblings.dgswchat.presentation.utils.extension.repeatOnStarted

@AndroidEntryPoint
class AddFragment: BaseFragment<FragmentAddBinding, AddViewModel>(R.layout.fragment_add) {

    override val viewModel: AddViewModel by viewModels()
    private var tag: String? = null

    override fun start() {
        with(mViewModel) {
            repeatOnStarted { eventFlow.collect { event -> handleEvent(event) } }

            binding.apply {
                btnSubmit.setOnClickListener {
                    tag?.let {
                        submitPost(etTitle.text.toString(), tag!!, etContent.text.toString())
                    }

                }

                schoolTag.setOnClickListener {
                    tag = "Tech"
                    schoolTag.setCardBackgroundColor(resources.getColor(R.color.chat))
                    tvSchool.setTextColor(resources.getColor(R.color.main))
                    techTag.setCardBackgroundColor(resources.getColor(R.color.gray2))
                    tvTech.setTextColor(resources.getColor(R.color.gray))
                }

                techTag.setOnClickListener {
                    tag = "School"
                    schoolTag.setCardBackgroundColor(resources.getColor(R.color.gray2))
                    tvSchool.setTextColor(resources.getColor(R.color.gray))
                    techTag.setCardBackgroundColor(resources.getColor(R.color.chat))
                    tvTech.setTextColor(resources.getColor(R.color.main))
                }
                
                btnBack.setOnClickListener {
                    requireActivity().supportFragmentManager.popBackStack()
                }
            }
        }
    }

    private fun handleEvent(event: AddViewModel.Event): Any =
        when (event) {
            is AddViewModel.Event.SuccessSubmit -> requireActivity().supportFragmentManager.popBackStack()
            is AddViewModel.Event.UnkownException -> shortToast("알 수 없는 오류가 발생했습니다.")
        }
}