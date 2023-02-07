package kr.hs.dgsw.woowasiblings.dgswchat.data.network.response.auth

data class TokenResponse(
    val accessToken: String,
    val refreshToken: String
)
