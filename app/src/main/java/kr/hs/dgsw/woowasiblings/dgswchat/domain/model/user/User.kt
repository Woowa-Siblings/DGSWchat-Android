package kr.hs.dgsw.woowasiblings.dgswchat.domain.model.user

import kr.hs.dgsw.woowasiblings.dgswchat.domain.model.post.Post

data class User(
    val user: UserInfo,
    val post: List<Post>
)
