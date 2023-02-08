package kr.hs.dgsw.woowasiblings.dgswchat.domain.repository

import kr.hs.dgsw.woowasiblings.dgswchat.domain.model.comment.Comment
import kr.hs.dgsw.woowasiblings.dgswchat.domain.model.comment.CommentDto

interface CommentRepository {

    suspend fun getComment(
        postId: Int
    ): List<Comment>

    suspend fun submitComment(
        commentDto: CommentDto
    )
}