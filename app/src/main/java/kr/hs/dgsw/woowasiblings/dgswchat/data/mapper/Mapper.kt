package kr.hs.dgsw.woowasiblings.dgswchat.data.mapper

import kr.hs.dgsw.woowasiblings.dgswchat.data.database.entity.ChatEntity
import kr.hs.dgsw.woowasiblings.dgswchat.data.network.request.ChatRequest
import kr.hs.dgsw.woowasiblings.dgswchat.data.network.response.chat.ChatResponse
import kr.hs.dgsw.woowasiblings.dgswchat.domain.model.Chat
import kr.hs.dgsw.woowasiblings.dgswchat.domain.model.ChatDto
import java.time.LocalTime

fun ChatEntity.toEntity(): Chat = Chat(
    time = this.time,
    message = this.message,
    isAuthor = this.isAuthor
)

fun Chat.toModel(): ChatEntity = ChatEntity(
    time = this.time,
    message = this.message,
    isAuthor = this.isAuthor
)

fun ChatDto.toModel(): ChatRequest = ChatRequest(
    includeAiFilters = false,
    includeProbs = false,
    includeTokens = false,
    maxTokens = 300,
    repeatPenalty = 5.0,
    restart = "",
    start = "",
    stopBefore = listOf("<|endoftext|>"),
    temperature = 0.85,
    text = this.text,
    topK = 4,
    topP = 0.8
)

fun ChatResponse.toEntity(): Chat = Chat(
    message = this.result.text,
    time = LocalTime.now(),
    isAuthor = 0
)