package kr.hs.dgsw.woowasiblings.dgswchat.domain.model.post

data class Post(
    val author: Int,
    val content: String,
    val grade: Int,
    val number: Int,
    val postId: Int,
    val room: Int,
    val tag: String,
    val title: String,
    val userName: String
)
