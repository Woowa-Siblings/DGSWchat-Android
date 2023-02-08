package kr.hs.dgsw.woowasiblings.dgswchat.domain.repository

import kr.hs.dgsw.woowasiblings.dgswchat.domain.model.auth.*
import kr.hs.dgsw.woowasiblings.dgswchat.domain.model.user.User

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

    suspend fun getUser(
    ): User
}