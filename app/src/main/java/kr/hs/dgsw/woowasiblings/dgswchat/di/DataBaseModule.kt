package kr.hs.dgsw.woowasiblings.dgswchat.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kr.hs.dgsw.woowasiblings.dgswchat.data.database.ChatDatabase
import kr.hs.dgsw.woowasiblings.dgswchat.data.database.dao.ChatDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideChatDataBase(@ApplicationContext context: Context): ChatDatabase =
        Room.databaseBuilder(context, ChatDatabase::class.java, "chat_database")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideChatDao(database: ChatDatabase): ChatDao =
        database.chatDao()
}