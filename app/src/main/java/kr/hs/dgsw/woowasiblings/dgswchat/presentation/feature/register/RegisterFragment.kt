package kr.hs.dgsw.woowasiblings.dgswchat.presentation.feature.register

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.woowasiblings.dgswchat.R
import kr.hs.dgsw.woowasiblings.dgswchat.databinding.FragmentRegisterBinding
import kr.hs.dgsw.woowasiblings.dgswchat.domain.model.auth.RegisterDto
import kr.hs.dgsw.woowasiblings.dgswchat.presentation.base.BaseFragment
import kr.hs.dgsw.woowasiblings.dgswchat.presentation.utils.extension.repeatOnStarted

@AndroidEntryPoint
class RegisterFragment: BaseFragment<FragmentRegisterBinding, RegisterViewModel>(R.layout.fragment_register) {

    override val viewModel: RegisterViewModel by viewModels()

    override fun start() {
        with(mViewModel) {
            repeatOnStarted { eventFlow.collect { event -> handleEvent(event) } }

            binding.apply {
                btnRegister.setOnClickListener {
                    register(
                        RegisterDto(
                            userId = etId.text.toString(),
                            nickname = etName.text.toString(),
                            password = encryptSHA512(etPw.text.toString()),
                            grade = etGrade.text.toString().toInt(),
                            room = etRoom.text.toString().toInt(),
                            number = etNumber.text.toString().toInt()
                        )
                    )
                }
            }
        }
    }

    private fun handleEvent(event: RegisterViewModel.Event): Any =
        when (event) {
            is RegisterViewModel.Event.SuccessRegister -> {
                requireActivity().supportFragmentManager.popBackStack()
                shortToast("회원가입에 성공하였습니다.")
            }
            is RegisterViewModel.Event.UnkownException -> shortToast("알 수 없는 오류가 발생했습니다.")
        }
}