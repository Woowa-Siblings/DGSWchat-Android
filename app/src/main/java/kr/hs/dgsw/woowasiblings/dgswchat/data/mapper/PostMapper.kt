package kr.hs.dgsw.woowasiblings.dgswchat.data.mapper

import kr.hs.dgsw.woowasiblings.dgswchat.data.network.request.post.PostSubmitRequest
import kr.hs.dgsw.woowasiblings.dgswchat.data.network.response.post.PostResponse
import kr.hs.dgsw.woowasiblings.dgswchat.domain.model.post.Post
import kr.hs.dgsw.woowasiblings.dgswchat.domain.model.post.PostDto

fun PostResponse.toEntity(): Post = Post(
    author = this.author,
    content = this.content,
    grade = this.grade,
    number = this.number,
    postId = this.postId,
    room = this.room,
    tag = this.tag,
    title = this.title,
    userName = this.userName
)

fun PostDto.toModel(): PostSubmitRequest = PostSubmitRequest(
    title = this.title,
    tag = this.tag,
    content = this.content
)