package kr.hs.dgsw.woowasiblings.dgswchat.domain.model.notice

data class Notice(
    val postId: Int,
    val senderName: String,
    val commentContent: String,
    val createDateTime: String,
    val noticeStatus: String
)