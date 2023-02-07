package kr.hs.dgsw.woowasiblings.dgswchat.presentation.feature.login

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.hs.dgsw.woowasiblings.dgswchat.App
import kr.hs.dgsw.woowasiblings.dgswchat.domain.model.auth.LoginDto
import kr.hs.dgsw.woowasiblings.dgswchat.domain.model.auth.TokenDto
import kr.hs.dgsw.woowasiblings.dgswchat.domain.model.chat.Chat
import kr.hs.dgsw.woowasiblings.dgswchat.domain.repository.AuthRepository
import kr.hs.dgsw.woowasiblings.dgswchat.presentation.base.BaseViewModel
import kr.hs.dgsw.woowasiblings.dgswchat.presentation.utils.MutableEventFlow
import kr.hs.dgsw.woowasiblings.dgswchat.presentation.utils.asEventFlow
import java.security.MessageDigest
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : BaseViewModel() {

    private val _eventFLow = MutableEventFlow<Event>()
    val eventFlow = _eventFLow.asEventFlow()

    fun login(loginDto: LoginDto) = viewModelScope.launch(Dispatchers.IO) {
        kotlin.runCatching {
            authRepository.login(loginDto)
        }.onSuccess {
            event(Event.SuccessLogin(it))
        }.onFailure {
            event(Event.UnkownException)
            Log.d("error1", "${it.message}")
        }
    }

    fun token(tokenDto: TokenDto) = viewModelScope.launch(Dispatchers.IO) {
        kotlin.runCatching {
            authRepository.token(tokenDto)
        }.onSuccess {
            App.prefs.accessToken = it.accessToken
            App.prefs.refreshToken = it.refreshToken
//            App.prefs.autoLogin = true
            event(Event.SuccessToken)
        }.onFailure {
            event(Event.UnkownException)
            Log.d("error2", "${it.message}")
        }
    }

    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFLow.emit(event)
        }
    }

    fun encryptSHA512(target: String): String {
        val messageDigest =
            MessageDigest.getInstance("SHA-512")
        val encryptedPassword = StringBuilder()
        messageDigest.update(target.toByteArray())
        val buffer = messageDigest.digest()
        for (temp in buffer) {
            var sb =
                StringBuilder(Integer.toHexString(temp.toInt()))
            while (sb.length < 2) {
                sb.insert(0, "0")
            }
            sb = StringBuilder(sb.substring(sb.length - 2))
            encryptedPassword.append(sb)
        }
        return encryptedPassword.toString().uppercase()
    }

    sealed class Event {
        data class SuccessLogin(val code: String): Event()
        object SuccessToken: Event()
        object UnkownException: Event()
    }
}