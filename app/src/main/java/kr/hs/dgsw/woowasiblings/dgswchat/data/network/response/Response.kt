package kr.hs.dgsw.woowasiblings.dgswchat.data.network.response

data class Response<T>(
    val status: Int,
    val message: String,
    val data: T
)
