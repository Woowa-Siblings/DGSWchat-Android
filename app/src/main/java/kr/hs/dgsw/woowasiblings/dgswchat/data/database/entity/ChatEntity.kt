package kr.hs.dgsw.woowasiblings.dgswchat.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalTime

@Entity(tableName = "chat_entity")
data class ChatEntity(
    @PrimaryKey
    val time: LocalTime,
    val message: String,
    val isAuthor: Int,
)
