package kr.hs.dgsw.woowasiblings.dgswchat.presentation.feature.chat

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.hs.dgsw.woowasiblings.dgswchat.domain.model.chat.Chat
import kr.hs.dgsw.woowasiblings.dgswchat.domain.model.chat.ChatDto
import kr.hs.dgsw.woowasiblings.dgswchat.domain.repository.ChatRepository
import kr.hs.dgsw.woowasiblings.dgswchat.presentation.base.BaseViewModel
import kr.hs.dgsw.woowasiblings.dgswchat.presentation.utils.MutableEventFlow
import kr.hs.dgsw.woowasiblings.dgswchat.presentation.utils.asEventFlow
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatRepository: ChatRepository
) : BaseViewModel() {

    init {
        getAllChats()
    }

    private val _eventFLow = MutableEventFlow<Event>()
    val eventFlow = _eventFLow.asEventFlow()

    fun getChatMessage(message: String) = viewModelScope.launch(Dispatchers.IO) {
        kotlin.runCatching {
            chatRepository.getChatMessage(
                ChatDto(message)
            )
        }.onSuccess {
            event(Event.SuccessChat(it))
        }.onFailure {
            event(Event.UnkownException)
            Log.d("error1", "${it.message}")
        }
    }

    fun getAllChats() = viewModelScope.launch(Dispatchers.IO) {
        kotlin.runCatching {
            chatRepository.getAllMessages()
        }.onSuccess {
            event(Event.SuccessAllChats(it))
        }.onFailure {
            Log.d("error2", "${it.message}")
            event(Event.UnkownException)
        }
    }

    fun insertChat(chat: Chat) = viewModelScope.launch(Dispatchers.IO) {
        kotlin.runCatching {
            chatRepository.insertChat(chat)
        }.onSuccess {
            event(Event.SuccessInsert)
        }.onFailure {
            Log.d("error3", "${it.message}")
            event(Event.UnkownException)
        }
    }

    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFLow.emit(event)
        }
    }

    sealed class Event {
        data class SuccessChat(val chat: Chat): Event()
        data class SuccessAllChats(val chats: List<Chat>): Event()
        object UnkownException: Event()
        object SuccessInsert: Event()
    }
}