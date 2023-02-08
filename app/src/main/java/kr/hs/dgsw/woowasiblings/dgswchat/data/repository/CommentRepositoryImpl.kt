package kr.hs.dgsw.woowasiblings.dgswchat.data.repository

import kr.hs.dgsw.woowasiblings.dgswchat.data.mapper.toEntity
import kr.hs.dgsw.woowasiblings.dgswchat.data.mapper.toModel
import kr.hs.dgsw.woowasiblings.dgswchat.data.network.api.CommentApi
import kr.hs.dgsw.woowasiblings.dgswchat.domain.model.comment.Comment
import kr.hs.dgsw.woowasiblings.dgswchat.domain.model.comment.CommentDto
import kr.hs.dgsw.woowasiblings.dgswchat.domain.repository.CommentRepository
import javax.inject.Inject

class CommentRepositoryImpl @Inject constructor(
    private val commentApi: CommentApi
) : CommentRepository {
    override suspend fun getComment(postId: Int): List<Comment> =
        commentApi.getComment(postId).data.map { it.toEntity() }

    override suspend fun submitComment(commentDto: CommentDto) =
        commentApi.submitComment(commentDto.toModel()).data

}