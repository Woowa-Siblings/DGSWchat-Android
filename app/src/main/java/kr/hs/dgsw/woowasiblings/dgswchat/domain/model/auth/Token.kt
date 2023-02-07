package kr.hs.dgsw.woowasiblings.dgswchat.domain.model.auth

data class Token(
    val accessToken: String,
    val refreshToken: String
)
