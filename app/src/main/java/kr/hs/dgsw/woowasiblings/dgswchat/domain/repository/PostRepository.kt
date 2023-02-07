package kr.hs.dgsw.woowasiblings.dgswchat.domain.repository

import kr.hs.dgsw.woowasiblings.dgswchat.domain.model.post.Post
import kr.hs.dgsw.woowasiblings.dgswchat.domain.model.post.PostDto

interface PostRepository {

    suspend fun submitPost(
         postDto: PostDto
    )

    suspend fun searchPost(
        keyword: String
    ): List<Post>

    suspend fun getPostByPostId(
        postId: Int
    ): Post

    suspend fun getPost(
    ): List<Post>

    suspend fun getPostByTag(
        tag: String
    ): List<Post>

    suspend fun deletePostByPostId(
        postId: Int
    )
}