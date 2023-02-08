package kr.hs.dgsw.woowasiblings.dgswchat.data.mapper

import kr.hs.dgsw.woowasiblings.dgswchat.data.network.request.auth.LoginRequest
import kr.hs.dgsw.woowasiblings.dgswchat.data.network.request.auth.RegisterRequest
import kr.hs.dgsw.woowasiblings.dgswchat.data.network.request.auth.TokenRequest
import kr.hs.dgsw.woowasiblings.dgswchat.data.network.response.auth.LoginResponse
import kr.hs.dgsw.woowasiblings.dgswchat.data.network.response.auth.RefreshTokenResponse
import kr.hs.dgsw.woowasiblings.dgswchat.data.network.response.auth.TokenResponse
import kr.hs.dgsw.woowasiblings.dgswchat.data.network.response.user.UserInfoResponse
import kr.hs.dgsw.woowasiblings.dgswchat.data.network.response.user.UserResponse
import kr.hs.dgsw.woowasiblings.dgswchat.domain.model.auth.*
import kr.hs.dgsw.woowasiblings.dgswchat.domain.model.user.User
import kr.hs.dgsw.woowasiblings.dgswchat.domain.model.user.UserInfo

fun LoginDto.toModel(): LoginRequest = LoginRequest(
    userId = this.userId,
    password = this.password
)

fun RegisterDto.toModel(): RegisterRequest = RegisterRequest(
    userId = this.userId,
    nickname = this.nickname,
    password = this.password,
    grade = this.grade,
    room = this.room,
    number = this.number
)

fun TokenDto.toModel(): TokenRequest = TokenRequest(
    authCode = this.authCode
)

fun TokenResponse.toEntity(): Token = Token(
    accessToken = this.accessToken,
    refreshToken = this.refreshToken
)

fun RefreshTokenResponse.toEntity(): RefreshToken = RefreshToken(
    accessToken = this.accessToken
)

fun LoginResponse.toEntity(): Login = Login(
    authCode = this.authCode
)

fun UserResponse.toEntity(): User = User(
    user = this.user.toEntity(),
    post = this.post.map { it.toEntity() }
)

fun UserInfoResponse.toEntity(): UserInfo = UserInfo(
    userId = this.userId,
    nickname = this.nickname,
    grade = this.grade,
    room = this.room,
    number = this.number
)