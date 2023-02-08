package kr.hs.dgsw.woowasiblings.dgswchat.presentation.feature.user

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.hs.dgsw.woowasiblings.dgswchat.domain.model.user.User
import kr.hs.dgsw.woowasiblings.dgswchat.domain.repository.AuthRepository
import kr.hs.dgsw.woowasiblings.dgswchat.presentation.base.BaseViewModel
import kr.hs.dgsw.woowasiblings.dgswchat.presentation.utils.MutableEventFlow
import kr.hs.dgsw.woowasiblings.dgswchat.presentation.utils.asEventFlow
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : BaseViewModel() {

    init {
        getUser()
    }

    private val _eventFLow = MutableEventFlow<Event>()
    val eventFlow = _eventFLow.asEventFlow()

    fun getUser() = viewModelScope.launch(Dispatchers.IO) {
        kotlin.runCatching {
            authRepository.getUser()
        }.onSuccess {
            event(Event.SuccessUser(it))
        }.onFailure {
            event(Event.UnkownException)
        }
    }

    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFLow.emit(event)
        }
    }

    sealed class Event {
        data class SuccessUser(val user: User): Event()
        object UnkownException: Event()
    }
}