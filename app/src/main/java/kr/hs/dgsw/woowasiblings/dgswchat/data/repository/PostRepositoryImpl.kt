package kr.hs.dgsw.woowasiblings.dgswchat.data.repository

import kr.hs.dgsw.woowasiblings.dgswchat.data.mapper.toEntity
import kr.hs.dgsw.woowasiblings.dgswchat.data.mapper.toModel
import kr.hs.dgsw.woowasiblings.dgswchat.data.network.api.PostApi
import kr.hs.dgsw.woowasiblings.dgswchat.domain.model.post.Post
import kr.hs.dgsw.woowasiblings.dgswchat.domain.model.post.PostDto
import kr.hs.dgsw.woowasiblings.dgswchat.domain.repository.PostRepository
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val postApi: PostApi
) : PostRepository {

    override suspend fun submitPost(postDto: PostDto) =
        postApi.submitPost(postDto.toModel()).data

    override suspend fun searchPost(keyword: String): List<Post> =
        postApi.searchPost(keyword).data.map { it.toEntity() }

    override suspend fun getPostByPostId(postId: Int): Post =
        postApi.getPostByPostId(postId).data.toEntity()

    override suspend fun getPost(): List<Post> =
        postApi.getPost().data.map { it.toEntity() }

    override suspend fun getPostByTag(tag: String): List<Post> =
        postApi.getPostByTag(tag).data.map { it.toEntity() }

    override suspend fun deletePostByPostId(postId: Int) =
        postApi.deletePostByPostId(postId).data

}