package kr.hs.dgsw.woowasiblings.dgswchat.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kr.hs.dgsw.woowasiblings.dgswchat.data.database.entity.ChatEntity

@Dao
interface ChatDao {

    @Insert
    suspend fun insertChatMessage(chat: ChatEntity)

    @Query("SELECT * FROM chat_entity")
    suspend fun getAllChatMessage(): List<ChatEntity>

    @Delete
    suspend fun deleteAll(chats: List<ChatEntity>)
}