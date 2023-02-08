package kr.hs.dgsw.woowasiblings.dgswchat.domain.repository

import kr.hs.dgsw.woowasiblings.dgswchat.domain.model.auth.*

interface AuthRepository {

    suspend fun login(
        loginDto: LoginDto
    ): Login

    suspend fun refreshToken(
    ): RefreshToken

    suspend fun token(
        tokenDto: TokenDto
    ): Token

    suspend fun register(
        registerDto: RegisterDto
    )
}