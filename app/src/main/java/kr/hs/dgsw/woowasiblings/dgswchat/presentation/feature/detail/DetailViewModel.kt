package kr.hs.dgsw.woowasiblings.dgswchat.presentation.feature.detail

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.hs.dgsw.woowasiblings.dgswchat.domain.model.comment.Comment
import kr.hs.dgsw.woowasiblings.dgswchat.domain.model.comment.CommentDto
import kr.hs.dgsw.woowasiblings.dgswchat.domain.model.post.Post
import kr.hs.dgsw.woowasiblings.dgswchat.domain.repository.CommentRepository
import kr.hs.dgsw.woowasiblings.dgswchat.domain.repository.PostRepository
import kr.hs.dgsw.woowasiblings.dgswchat.presentation.base.BaseViewModel
import kr.hs.dgsw.woowasiblings.dgswchat.presentation.utils.MutableEventFlow
import kr.hs.dgsw.woowasiblings.dgswchat.presentation.utils.asEventFlow
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val commentRepository: CommentRepository,
    private val postRepository: PostRepository
) : BaseViewModel() {

    private val _eventFLow = MutableEventFlow<Event>()
    val eventFlow = _eventFLow.asEventFlow()

    fun getComment(postId: Int) = viewModelScope.launch(Dispatchers.IO) {
        kotlin.runCatching {
            commentRepository.getComment(postId)
        }.onSuccess {
            event(Event.SuccessComment(it))
        }.onFailure {
            event(Event.UnkownException)
        }
    }

    fun submitComment(commentDto: CommentDto) = viewModelScope.launch(Dispatchers.IO) {
        kotlin.runCatching {
            commentRepository.submitComment(commentDto)
        }.onSuccess {
            event(Event.SuccessSubmit)
        }.onFailure {
            event(Event.UnkownException)
            Log.d("error", "${it.message}")
        }
    }

    fun getPostByPostId(postId: Int) = viewModelScope.launch(Dispatchers.IO) {
        kotlin.runCatching {
            postRepository.getPostByPostId(postId)
        }.onSuccess {
            event(Event.SuccessPostByPostId(it))
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
        data class SuccessPostByPostId(val post: Post): Event()
        data class SuccessComment(val comments: List<Comment>): Event()
        object UnkownException: Event()
    }
}