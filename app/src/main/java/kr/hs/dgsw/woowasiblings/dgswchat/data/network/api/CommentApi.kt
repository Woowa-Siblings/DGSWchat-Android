package kr.hs.dgsw.woowasiblings.dgswchat.data.network.api

import kr.hs.dgsw.woowasiblings.dgswchat.data.network.request.comment.CommentRequest
import kr.hs.dgsw.woowasiblings.dgswchat.data.network.response.Response
import kr.hs.dgsw.woowasiblings.dgswchat.data.network.response.comment.CommentResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CommentApi {

    @GET("comment/read/{postId}")
    suspend fun getComment(
        @Path("postId") postId: Int
    ): Response<List<CommentResponse>>

    @POST("comment/submit")
    suspend fun submitComment(
        @Body commentRequest: CommentRequest
    ): Response<Unit>

    @DELETE("comment/delete/{id}")
    suspend fun deleteComment(
        @Path("id") id: Int
    ): Response<Unit>
}