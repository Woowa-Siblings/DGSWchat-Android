package kr.hs.dgsw.woowasiblings.dgswchat.data.network.request.auth

data class RegisterRequest(
    val userId: String,
    val nickname: String,
    val password: String,
    val grade: Int,
    val room: Int,
    val number: Int
)
