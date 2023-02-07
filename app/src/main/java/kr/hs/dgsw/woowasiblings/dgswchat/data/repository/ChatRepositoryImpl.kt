package kr.hs.dgsw.woowasiblings.dgswchat.data.repository

import kr.hs.dgsw.woowasiblings.dgswchat.data.database.dao.ChatDao
import kr.hs.dgsw.woowasiblings.dgswchat.data.mapper.toEntity
import kr.hs.dgsw.woowasiblings.dgswchat.data.mapper.toModel
import kr.hs.dgsw.woowasiblings.dgswchat.data.network.api.ChatApi
import kr.hs.dgsw.woowasiblings.dgswchat.domain.model.chat.Chat
import kr.hs.dgsw.woowasiblings.dgswchat.domain.model.chat.ChatDto
import kr.hs.dgsw.woowasiblings.dgswchat.domain.repository.ChatRepository
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val chatApi: ChatApi,
    private val chatDao: ChatDao
): ChatRepository {

    override suspend fun getChatMessage(chatDto: ChatDto): Chat =
        chatApi.getChatMessage(chatDto.toModel()).toEntity()

    override suspend fun getAllMessages(): List<Chat> =
        chatDao.getAllChatMessage().map { it.toEntity() }

    override suspend fun insertChat(chat: Chat) =
        chatDao.insertChatMessage(chat.toModel())
}
