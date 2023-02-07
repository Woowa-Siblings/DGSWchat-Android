package kr.hs.dgsw.woowasiblings.dgswchat.domain.model

import java.time.LocalTime

data class Chat(
    val time: LocalTime,
    val message: String,
    val isAuthor: Int
)
