package kr.hs.dgsw.woowasiblings.dgswchat.data.network.api

import kr.hs.dgsw.woowasiblings.dgswchat.data.network.request.LoginRequest
import kr.hs.dgsw.woowasiblings.dgswchat.data.network.request.RegisterRequest
import kr.hs.dgsw.woowasiblings.dgswchat.data.network.request.TokenRequest
import kr.hs.dgsw.woowasiblings.dgswchat.data.network.response.auth.RefreshTokenResponse
import kr.hs.dgsw.woowasiblings.dgswchat.data.network.response.auth.TokenResponse
import kr.hs.dgsw.woowasiblings.dgswchat.data.util.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApi {

    @POST("auth/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Response<String>

    @GET("auth/refreshToken")
    suspend fun refreshToken(
    ): Response<RefreshTokenResponse>

    @POST("auth/token")
    suspend fun token(
        @Body tokenRequest: TokenRequest
    ): Response<TokenResponse>

    @POST("auth/register")
    suspend fun register(
        @Body registerRequest: RegisterRequest
    ): Response<Unit>
}
