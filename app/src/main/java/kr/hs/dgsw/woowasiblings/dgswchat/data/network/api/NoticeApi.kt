package kr.hs.dgsw.woowasiblings.dgswchat.data.network.api

import kr.hs.dgsw.woowasiblings.dgswchat.data.network.response.notice.NoticeResponse
import kr.hs.dgsw.woowasiblings.dgswchat.data.network.response.notice.NoticeStatusResponse
import kr.hs.dgsw.woowasiblings.dgswchat.data.network.response.Response
import retrofit2.http.GET

interface NoticeApi {

    @GET("notice/list")
    suspend fun getNotice(
    ): Response<List<NoticeResponse>>

    @GET("notice/check")
    suspend fun checkNotice(
    ): Response<NoticeStatusResponse>
}