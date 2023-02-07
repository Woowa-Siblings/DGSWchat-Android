package kr.hs.dgsw.woowasiblings.dgswchat.presentation.feature.chat

import android.widget.Toast
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.woowasiblings.dgswchat.R
import kr.hs.dgsw.woowasiblings.dgswchat.databinding.FragmentChatBinding
import kr.hs.dgsw.woowasiblings.dgswchat.domain.model.Chat
import kr.hs.dgsw.woowasiblings.dgswchat.presentation.base.BaseFragment
import kr.hs.dgsw.woowasiblings.dgswchat.presentation.utils.extension.repeatOnStarted
import kr.hs.dgsw.woowasiblings.dgswchat.presentation.feature.chat.ChatViewModel.Event
import java.time.LocalTime


@AndroidEntryPoint
class ChatFragment: BaseFragment<FragmentChatBinding, ChatViewModel>(R.layout.fragment_chat) {

    override val viewModel: ChatViewModel by viewModels()
    private lateinit var chatAdapter: ChatAdapter

    override fun start() {
        with(mViewModel) {
            repeatOnStarted { eventFlow.collect { event -> handleEvent(event) } }

            binding.apply {
                btnSend.setOnClickListener {
                    getChatMessage(etMessage.text.toString())
                    initAdapter(Chat(
                        time = LocalTime.now(),
                        message = etMessage.text.toString(),
                        isAuthor = 1
                    ))
                }
            }
        }
    }

    private fun initAdapter(chat: Chat) {
        chatAdapter = ChatAdapter()
        chatAdapter.chats.add(chat)
        binding.rvChat.adapter = chatAdapter
    }

    private fun handleEvent(event: Event): Any =
        when (event) {
            is Event.SuccessChat -> initAdapter(event.chat)
            is Event.UnkownException -> shortToast("알 수 없는 오류가 발생했습니다.")
        }

    private fun shortToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}