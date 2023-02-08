package kr.hs.dgsw.woowasiblings.dgswchat.domain.model.user

data class UserInfo(
    val userId: String,
    val nickname: String,
    val grade: Int,
    val room: Int,
    val number: Int
)
