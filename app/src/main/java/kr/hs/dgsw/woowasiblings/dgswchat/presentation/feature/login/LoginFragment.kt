package kr.hs.dgsw.woowasiblings.dgswchat.presentation.feature.login

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.woowasiblings.dgswchat.R
import kr.hs.dgsw.woowasiblings.dgswchat.databinding.FragmentLoginBinding
import kr.hs.dgsw.woowasiblings.dgswchat.presentation.base.BaseFragment

@AndroidEntryPoint
class LoginFragment: BaseFragment<FragmentLoginBinding, LoginViewModel>(R.layout.fragment_login) {

    override val viewModel: LoginViewModel by viewModels()

    override fun start() {

    }
}