package kr.hs.dgsw.woowasiblings.dgswchat.presentation.feature.add

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.hs.dgsw.woowasiblings.dgswchat.domain.model.post.PostDto
import kr.hs.dgsw.woowasiblings.dgswchat.domain.repository.PostRepository
import kr.hs.dgsw.woowasiblings.dgswchat.presentation.base.BaseViewModel
import kr.hs.dgsw.woowasiblings.dgswchat.presentation.utils.MutableEventFlow
import kr.hs.dgsw.woowasiblings.dgswchat.presentation.utils.asEventFlow
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    private val postRepository: PostRepository
) : BaseViewModel() {

    private val _eventFLow = MutableEventFlow<Event>()
    val eventFlow = _eventFLow.asEventFlow()

    fun submitPost(title: String, tag: String, content: String) = viewModelScope.launch(Dispatchers.IO) {
        kotlin.runCatching {
            postRepository.submitPost(
                PostDto(title, tag, content)
            )
        }.onSuccess {
            event(Event.SuccessSubmit)
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
        object SuccessSubmit: Event()
        object UnkownException: Event()
    }
}