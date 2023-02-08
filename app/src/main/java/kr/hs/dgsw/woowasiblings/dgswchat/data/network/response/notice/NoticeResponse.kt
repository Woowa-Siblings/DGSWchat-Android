package kr.hs.dgsw.woowasiblings.dgswchat.data.network.response.notice

data class NoticeResponse(
    val postId: Int,
    val senderName: String,
    val commentContent: String,
    val createDateTime: String,
    val noticeStatus: String
)
