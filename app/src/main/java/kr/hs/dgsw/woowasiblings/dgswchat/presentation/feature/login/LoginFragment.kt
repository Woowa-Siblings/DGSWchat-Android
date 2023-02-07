package kr.hs.dgsw.woowasiblings.dgswchat.presentation.feature.login

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.woowasiblings.dgswchat.R
import kr.hs.dgsw.woowasiblings.dgswchat.databinding.FragmentLoginBinding
import kr.hs.dgsw.woowasiblings.dgswchat.domain.model.auth.LoginDto
import kr.hs.dgsw.woowasiblings.dgswchat.domain.model.auth.TokenDto
import kr.hs.dgsw.woowasiblings.dgswchat.presentation.base.BaseFragment
import kr.hs.dgsw.woowasiblings.dgswchat.presentation.feature.chat.ChatViewModel
import kr.hs.dgsw.woowasiblings.dgswchat.presentation.feature.home.HomeFragment
import kr.hs.dgsw.woowasiblings.dgswchat.presentation.feature.main.MainActivity
import kr.hs.dgsw.woowasiblings.dgswchat.presentation.utils.extension.repeatOnStarted

@AndroidEntryPoint
class LoginFragment: BaseFragment<FragmentLoginBinding, LoginViewModel>(R.layout.fragment_login) {

    override val viewModel: LoginViewModel by viewModels()

    override fun start() {
        with(mViewModel) {
            repeatOnStarted { eventFlow.collect { event -> handleEvent(event) } }

            binding.apply {
                btnLogin.setOnClickListener {
                    login(
                        LoginDto(
                            etId.text.toString(),
                            encryptSHA512(etPw.text.toString())
                        )
                    )
                }
                etId.setOnFocusChangeListener { _, hasFocus ->
                    if (hasFocus) etIdLayout.setStartIconDrawable(R.drawable.focus_user)
                    else etIdLayout.setStartIconDrawable(R.drawable.unfocus_user)
                }
                etPw.setOnFocusChangeListener { _, hasFocus ->
                    if (hasFocus) etPwLayout.setStartIconDrawable(R.drawable.focus_lock)
                    else etPwLayout.setStartIconDrawable(R.drawable.unfocus_lock)
                }
            }
        }
    }

    private fun handleEvent(event: LoginViewModel.Event): Any =
        when (event) {
            is LoginViewModel.Event.SuccessLogin -> mViewModel.token(TokenDto(event.code))
            is LoginViewModel.Event.SuccessToken -> {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment, HomeFragment())
                    .commit()
            }
            is LoginViewModel.Event.UnkownException -> shortToast("알 수 없는 오류가 발생했습니다.")
        }
}