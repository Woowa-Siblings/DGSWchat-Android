package kr.hs.dgsw.woowasiblings.dgswchat.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.hs.dgsw.woowasiblings.dgswchat.data.database.dao.ChatDao
import kr.hs.dgsw.woowasiblings.dgswchat.data.network.api.*
import kr.hs.dgsw.woowasiblings.dgswchat.data.repository.*
import kr.hs.dgsw.woowasiblings.dgswchat.domain.repository.*
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

    @Provides
    @Singleton
    fun provideAuthRepository(
        api: AuthApi
    ): AuthRepository {
        return AuthRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun providePostRepository(
        api: PostApi
    ): PostRepository {
        return PostRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideCommentRepository(
        api: CommentApi
    ): CommentRepository {
        return CommentRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideNoticeRepository(
        api: NoticeApi
    ): NoticeRepository {
        return NoticeRepositoryImpl(api)
    }
}