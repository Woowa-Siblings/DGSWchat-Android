package kr.hs.dgsw.woowasiblings.dgswchat.presentation.feature.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.hs.dgsw.woowasiblings.dgswchat.domain.model.chat.Chat
import kr.hs.dgsw.woowasiblings.dgswchat.domain.model.post.Post
import kr.hs.dgsw.woowasiblings.dgswchat.domain.repository.PostRepository
import kr.hs.dgsw.woowasiblings.dgswchat.presentation.base.BaseViewModel
import kr.hs.dgsw.woowasiblings.dgswchat.presentation.utils.MutableEventFlow
import kr.hs.dgsw.woowasiblings.dgswchat.presentation.utils.asEventFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val postRepository: PostRepository
) : BaseViewModel() {

    init {
        getPost()
    }

    private val _eventFLow = MutableEventFlow<Event>()
    val eventFlow = _eventFLow.asEventFlow()

    fun getPost() = viewModelScope.launch(Dispatchers.IO) {
        kotlin.runCatching {
            postRepository.getPost()
        }.onSuccess {
            event(Event.SuccessPost(it))
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
        data class SuccessPost(val posts: List<Post>): Event()
        object UnkownException: Event()
    }
}