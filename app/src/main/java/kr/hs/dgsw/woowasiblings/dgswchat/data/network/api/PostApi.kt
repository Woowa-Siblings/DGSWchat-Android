package kr.hs.dgsw.woowasiblings.dgswchat.data.network.api

import kr.hs.dgsw.woowasiblings.dgswchat.data.network.request.post.PostSubmitRequest
import kr.hs.dgsw.woowasiblings.dgswchat.data.network.response.post.PostResponse
import kr.hs.dgsw.woowasiblings.dgswchat.data.network.response.Response
import retrofit2.http.*

interface PostApi {

    @POST("post/submit")
    suspend fun submitPost(
        @Body postSubmitRequest: PostSubmitRequest
    ): Response<Unit>

    @GET("post/search/{keyword}")
    suspend fun searchPost(
        @Path("keyword") keyword: String
    ): Response<List<PostResponse>>

    @GET("post/read-one/{postId}")
    suspend fun getPostByPostId(
        @Path("postId") postId: Int
    ): Response<PostResponse>

    @GET("post/read-all")
    suspend fun getPost(
    ): Response<List<PostResponse>>

    @GET("post/read-all/{tag}")
    suspend fun getPostByTag(
        @Path("tag") tag: String
    ): Response<List<PostResponse>>

    @DELETE("post/delete/{postId}")
    suspend fun deletePostByPostId(
        @Path("postId") postId: Int
    ): Response<Unit>
}