package kr.hs.dgsw.woowasiblings.dgswchat.data.repository

import kr.hs.dgsw.woowasiblings.dgswchat.data.mapper.toEntity
import kr.hs.dgsw.woowasiblings.dgswchat.data.network.api.NoticeApi
import kr.hs.dgsw.woowasiblings.dgswchat.domain.model.notice.Notice
import kr.hs.dgsw.woowasiblings.dgswchat.domain.model.notice.NoticeStatus
import kr.hs.dgsw.woowasiblings.dgswchat.domain.repository.NoticeRepository
import javax.inject.Inject

class NoticeRepositoryImpl @Inject constructor(
    private val noticeApi: NoticeApi
) : NoticeRepository {

    override suspend fun getNotice(): List<Notice> =
        noticeApi.getNotice().data.map { it.toEntity() }

    override suspend fun checkNotice(): NoticeStatus =
        noticeApi.checkNotice().data.toEntity()

}