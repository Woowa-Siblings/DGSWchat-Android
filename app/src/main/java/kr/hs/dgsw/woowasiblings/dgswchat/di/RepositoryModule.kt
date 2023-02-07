package kr.hs.dgsw.woowasiblings.dgswchat.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.hs.dgsw.woowasiblings.dgswchat.data.database.dao.ChatDao
import kr.hs.dgsw.woowasiblings.dgswchat.data.network.api.ChatApi
import kr.hs.dgsw.woowasiblings.dgswchat.data.repository.ChatRepositoryImpl
import kr.hs.dgsw.woowasiblings.dgswchat.domain.repository.ChatRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideChatRepository(
        api: ChatApi,
        dao: ChatDao
    ): ChatRepository {
        return ChatRepositoryImpl(api, dao)
    }
}