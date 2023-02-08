package kr.hs.dgsw.woowasiblings.dgswchat.presentation.feature.register

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.hs.dgsw.woowasiblings.dgswchat.domain.model.auth.Login
import kr.hs.dgsw.woowasiblings.dgswchat.domain.model.auth.RegisterDto
import kr.hs.dgsw.woowasiblings.dgswchat.domain.repository.AuthRepository
import kr.hs.dgsw.woowasiblings.dgswchat.presentation.base.BaseViewModel
import kr.hs.dgsw.woowasiblings.dgswchat.presentation.feature.login.LoginViewModel
import kr.hs.dgsw.woowasiblings.dgswchat.presentation.utils.MutableEventFlow
import kr.hs.dgsw.woowasiblings.dgswchat.presentation.utils.asEventFlow
import java.security.MessageDigest
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : BaseViewModel() {

    private val _eventFLow = MutableEventFlow<Event>()
    val eventFlow = _eventFLow.asEventFlow()

    fun register(registerDto: RegisterDto) = viewModelScope.launch(Dispatchers.IO) {
        kotlin.runCatching {
            authRepository.register(registerDto)
        }.onSuccess {
            event(Event.SuccessRegister)
        }.onFailure {
            event(Event.UnkownException)
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
        object SuccessRegister: Event()
        object UnkownException: Event()
    }
}