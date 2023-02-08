package kr.hs.dgsw.woowasiblings.dgswchat.data.network.response.user

import kr.hs.dgsw.woowasiblings.dgswchat.data.network.response.post.PostResponse

data class UserResponse(
    val user: UserInfoResponse,
    val post: List<PostResponse>
)
