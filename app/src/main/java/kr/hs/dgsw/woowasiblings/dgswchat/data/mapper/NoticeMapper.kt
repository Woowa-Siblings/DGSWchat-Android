package kr.hs.dgsw.woowasiblings.dgswchat.data.mapper

import kr.hs.dgsw.woowasiblings.dgswchat.data.network.response.notice.NoticeResponse
import kr.hs.dgsw.woowasiblings.dgswchat.data.network.response.notice.NoticeStatusResponse
import kr.hs.dgsw.woowasiblings.dgswchat.domain.model.notice.Notice
import kr.hs.dgsw.woowasiblings.dgswchat.domain.model.notice.NoticeStatus

fun NoticeResponse.toEntity(): Notice = Notice(
    postId = this.postId,
    senderName = this.senderName,
    commentContent = this.commentContent,
    createDateTime = this.createDateTime,
    noticeStatus = this.noticeStatus
)

fun NoticeStatusResponse.toEntity(): NoticeStatus = NoticeStatus(
    noticeStatus = this.noticeStatus
)
