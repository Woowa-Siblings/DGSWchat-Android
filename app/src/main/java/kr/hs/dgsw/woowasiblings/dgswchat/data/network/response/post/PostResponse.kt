package kr.hs.dgsw.woowasiblings.dgswchat.data.network.response.post

data class PostResponse(
    val author: Int,
    val content: String,
    val createDateTime: String,
    val grade: Int,
    val number: Int,
    val postId: Int,
    val room: Int,
    val tag: String,
    val title: String,
    val userName: String
)