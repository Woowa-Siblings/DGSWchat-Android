package kr.hs.dgsw.woowasiblings.dgswchat.domain.model.comment

data class Comment(
    val commentId: Int,
    val content: String,
    val createDateTime: String,
    val grade: Int,
    val number: Int,
    val postId: Int,
    val room: Int,
    val userId: Int,
    val userName: String
)
