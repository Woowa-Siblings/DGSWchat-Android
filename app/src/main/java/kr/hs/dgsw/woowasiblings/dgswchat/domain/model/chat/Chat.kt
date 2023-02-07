package kr.hs.dgsw.woowasiblings.dgswchat.domain.model.chat

data class Chat(
    val time: String,
    val message: String,
    val isAuthor: Int
)
