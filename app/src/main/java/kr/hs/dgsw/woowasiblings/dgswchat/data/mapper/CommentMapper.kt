package kr.hs.dgsw.woowasiblings.dgswchat.data.mapper

import kr.hs.dgsw.woowasiblings.dgswchat.data.network.request.comment.CommentRequest
import kr.hs.dgsw.woowasiblings.dgswchat.data.network.response.comment.CommentResponse
import kr.hs.dgsw.woowasiblings.dgswchat.domain.model.comment.Comment
import kr.hs.dgsw.woowasiblings.dgswchat.domain.model.comment.CommentDto

fun CommentResponse.toEntity(): Comment = Comment(
    commentId = this.commentId,
    content = this.content,
    createDateTime = this.createDateTime,
    grade = this.grade,
    number = number,
    postId = this.postId,
    room = this.room,
    userId = this.userId,
    userName = this.userName
)

fun CommentDto.toModel(): CommentRequest = CommentRequest(
    postId = this.postId,
    content = this.content
)