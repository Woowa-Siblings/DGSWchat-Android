package kr.hs.dgsw.woowasiblings.dgswchat.data.network.response.chat

data class Status(
    val code: String,
    val message: String,
    val messageVariables: MessageVariables
)