package kr.hs.dgsw.woowasiblings.dgswchat.data.repository

import kr.hs.dgsw.woowasiblings.dgswchat.data.mapper.toEntity
import kr.hs.dgsw.woowasiblings.dgswchat.data.mapper.toModel
import kr.hs.dgsw.woowasiblings.dgswchat.data.network.api.AuthApi
import kr.hs.dgsw.woowasiblings.dgswchat.domain.model.auth.*
import kr.hs.dgsw.woowasiblings.dgswchat.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi
) : AuthRepository {
    override suspend fun login(loginDto: LoginDto): String =
        authApi.login(loginDto.toModel()).data

    override suspend fun refreshToken(): RefreshToken =
        authApi.refreshToken().data.toEntity()

    override suspend fun token(tokenDto: TokenDto): Token =
        authApi.token(tokenDto.toModel()).data.toEntity()

    override suspend fun register(registerDto: RegisterDto) =
        authApi.register(registerDto.toModel()).data

}
