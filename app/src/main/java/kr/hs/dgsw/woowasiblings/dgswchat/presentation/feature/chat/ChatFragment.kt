package kr.hs.dgsw.woowasiblings.dgswchat.presentation.feature.chat

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.hs.dgsw.woowasiblings.dgswchat.R
import kr.hs.dgsw.woowasiblings.dgswchat.databinding.FragmentChatBinding
import kr.hs.dgsw.woowasiblings.dgswchat.domain.model.chat.Chat
import kr.hs.dgsw.woowasiblings.dgswchat.presentation.base.BaseFragment
import kr.hs.dgsw.woowasiblings.dgswchat.presentation.utils.extension.repeatOnStarted
import kr.hs.dgsw.woowasiblings.dgswchat.presentation.feature.chat.ChatViewModel.Event
import java.time.LocalTime


@AndroidEntryPoint
class ChatFragment : BaseFragment<FragmentChatBinding, ChatViewModel>(R.layout.fragment_chat) {

    override val viewModel: ChatViewModel by viewModels()

    override fun start() {
        with(mViewModel) {
            repeatOnStarted { eventFlow.collect { event -> handleEvent(event) } }

            lifecycleScope.launch(Dispatchers.Main) {

                binding.apply {
                    btnSend.setOnClickListener {
                        getChatMessage(etMessage.text.toString())
                        insertChat(
                            Chat(
                                time = LocalTime.now().toString(),
                                message = etMessage.text.toString(),
                                isAuthor = 1
                            )
                        )
                        etMessage.text = null
                    }
                }
            }
        }
    }

    private fun initAdapter(chat: List<Chat>) {
        val chatAdapter = ChatAdapter()
        chatAdapter.chats.addAll(chat)
        binding.rvChat.adapter = chatAdapter
    }

    private fun handleEvent(event: Event): Any =
        when (event) {
            is Event.SuccessChat -> mViewModel.insertChat(event.chat)
            is Event.SuccessInsert -> mViewModel.getAllChats()
            is Event.SuccessAllChats -> {
                initAdapter(event.chats)
                binding.rvChat.scrollToPosition(event.chats.size-1)
            }
            is Event.UnkownException -> shortToast("알 수 없는 오류가 발생했습니다.")
        }
}