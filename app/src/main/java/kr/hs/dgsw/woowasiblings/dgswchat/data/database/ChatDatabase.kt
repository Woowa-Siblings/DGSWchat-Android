package kr.hs.dgsw.woowasiblings.dgswchat.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import kr.hs.dgsw.woowasiblings.dgswchat.data.database.dao.ChatDao
import kr.hs.dgsw.woowasiblings.dgswchat.data.database.entity.ChatEntity

@Database(
    entities = [ChatEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ChatDatabase : RoomDatabase() {

    abstract fun chatDao() : ChatDao
}