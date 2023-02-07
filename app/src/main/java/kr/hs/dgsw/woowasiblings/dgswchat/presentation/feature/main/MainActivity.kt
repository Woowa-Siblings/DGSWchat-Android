package kr.hs.dgsw.woowasiblings.dgswchat.presentation.feature.main

import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.woowasiblings.dgswchat.R
import kr.hs.dgsw.woowasiblings.dgswchat.databinding.ActivityMainBinding
import kr.hs.dgsw.woowasiblings.dgswchat.presentation.base.BaseActivity
import kr.hs.dgsw.woowasiblings.dgswchat.presentation.feature.login.LoginFragment

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {

    override val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun start() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment, LoginFragment())
            .commit()
    }

}