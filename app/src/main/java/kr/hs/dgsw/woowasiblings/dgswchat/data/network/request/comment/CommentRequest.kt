package kr.hs.dgsw.woowasiblings.dgswchat.data.network.request.comment

data class CommentRequest(
    val postId: Int,
    val content: String
)