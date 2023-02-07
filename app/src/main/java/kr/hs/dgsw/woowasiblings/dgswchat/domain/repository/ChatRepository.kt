package kr.hs.dgsw.woowasiblings.dgswchat.domain.repository

import kr.hs.dgsw.woowasiblings.dgswchat.data.database.entity.ChatEntity
import kr.hs.dgsw.woowasiblings.dgswchat.domain.model.Chat
import kr.hs.dgsw.woowasiblings.dgswchat.domain.model.ChatDto

interface ChatRepository {

    suspend fun getChatMessage(
        chatDto: ChatDto
    ): Chat

    suspend fun getAllMessages(
    ): List<Chat>

    suspend fun insertChat(
        chat: Chat
    )
}