package kr.hs.dgsw.woowasiblings.dgswchat.data.network.request.post

data class PostSubmitRequest(
    val title: String,
    val tag: String,
    val content: String
)