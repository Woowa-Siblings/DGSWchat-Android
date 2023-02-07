package kr.hs.dgsw.woowasiblings.dgswchat.data.network.api

import kr.hs.dgsw.woowasiblings.dgswchat.data.network.request.ChatRequest
import kr.hs.dgsw.woowasiblings.dgswchat.data.network.response.chat.ChatResponse
import kr.hs.dgsw.woowasiblings.dgswchat.data.util.Content_Type
import kr.hs.dgsw.woowasiblings.dgswchat.data.util.X_NCP_APIGW_API_KEY
import kr.hs.dgsw.woowasiblings.dgswchat.data.util.X_NCP_CLOVASTUDIO_API_KEY
import kr.hs.dgsw.woowasiblings.dgswchat.data.util.X_NCP_CLOVASTUDIO_REQUEST_ID
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ChatApi {

    @Headers(
        "X-NCP-CLOVASTUDIO-API-KEY: $X_NCP_CLOVASTUDIO_API_KEY",
        "X-NCP-APIGW-API-KEY: $X_NCP_APIGW_API_KEY",
        "X-NCP-CLOVASTUDIO-REQUEST-ID: $X_NCP_CLOVASTUDIO_REQUEST_ID",
        "Content-Type: $Content_Type"
    )
    @POST("testapp/v1/tasks/x56jxyz7/completions/LK-B")
    suspend fun getChatMessage(
        @Body chatDto: ChatRequest
    ) : ChatResponse

}