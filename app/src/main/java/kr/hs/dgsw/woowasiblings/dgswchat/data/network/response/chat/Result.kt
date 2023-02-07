package kr.hs.dgsw.woowasiblings.dgswchat.data.network.response.chat

data class Result(
    val aiFilter: List<AiFilter>,
    val inputLength: Int,
    val inputTokens: List<Any>,
    val ok: Boolean,
    val outputLength: Int,
    val outputTokens: List<Any>,
    val probs: List<Any>,
    val stopReason: String,
    val text: String
)