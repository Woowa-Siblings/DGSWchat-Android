package kr.hs.dgsw.woowasiblings.dgswchat.domain.repository

import kr.hs.dgsw.woowasiblings.dgswchat.domain.model.notice.Notice
import kr.hs.dgsw.woowasiblings.dgswchat.domain.model.notice.NoticeStatus

interface NoticeRepository {

    suspend fun getNotice(
    ): List<Notice>

    suspend fun checkNotice(
    ): NoticeStatus
}