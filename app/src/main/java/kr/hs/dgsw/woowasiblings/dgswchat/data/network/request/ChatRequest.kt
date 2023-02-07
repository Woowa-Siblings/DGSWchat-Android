package kr.hs.dgsw.woowasiblings.dgswchat.data.network.request

data class ChatRequest(
    val includeAiFilters: Boolean,
    val includeProbs: Boolean,
    val includeTokens: Boolean,
    val maxTokens: Int,
    val repeatPenalty: Double,
    val restart: String,
    val start: String,
    val stopBefore: List<String>,
    val temperature: Double,
    val text: String,
    val topK: Int,
    val topP: Double
)