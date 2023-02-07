package kr.hs.dgsw.woowasiblings.dgswchat.data.util

data class Response<T>(
    val status: Int,
    val message: String,
    val data: T
)
