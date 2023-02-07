package kr.hs.dgsw.woowasiblings.dgswchat.presentation.feature.home

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.woowasiblings.dgswchat.R
import kr.hs.dgsw.woowasiblings.dgswchat.databinding.FragmentHomeBinding
import kr.hs.dgsw.woowasiblings.dgswchat.presentation.base.BaseFragment

@AndroidEntryPoint
class HomeFragment: BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home) {

    override val viewModel: HomeViewModel by viewModels()

    override fun start() {


    }
}