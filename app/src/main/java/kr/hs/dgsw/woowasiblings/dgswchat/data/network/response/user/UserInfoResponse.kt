package kr.hs.dgsw.woowasiblings.dgswchat.data.network.response.user

data class UserInfoResponse(
    val userId: String,
    val nickname: String,
    val grade: Int,
    val room: Int,
    val number: Int
)
