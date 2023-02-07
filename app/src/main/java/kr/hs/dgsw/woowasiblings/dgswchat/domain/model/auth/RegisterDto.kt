package kr.hs.dgsw.woowasiblings.dgswchat.domain.model.auth

data class RegisterDto(
    val userId: String,
    val nickname: String,
    val password: String,
    val grade: Int,
    val room: Int,
    val number: Int
)
