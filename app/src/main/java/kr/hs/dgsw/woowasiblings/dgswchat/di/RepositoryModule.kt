package kr.hs.dgsw.woowasiblings.dgswchat.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.hs.dgsw.woowasiblings.dgswchat.data.database.dao.ChatDao
import kr.hs.dgsw.woowasiblings.dgswchat.data.network.api.AuthApi
import kr.hs.dgsw.woowasiblings.dgswchat.data.network.api.ChatApi
import kr.hs.dgsw.woowasiblings.dgswchat.data.network.api.CommentApi
import kr.hs.dgsw.woowasiblings.dgswchat.data.network.api.PostApi
import kr.hs.dgsw.woowasiblings.dgswchat.data.repository.AuthRepositoryImpl
import kr.hs.dgsw.woowasiblings.dgswchat.data.repository.ChatRepositoryImpl
import kr.hs.dgsw.woowasiblings.dgswchat.data.repository.CommentRepositoryImpl
import kr.hs.dgsw.woowasiblings.dgswchat.data.repository.PostRepositoryImpl
import kr.hs.dgsw.woowasiblings.dgswchat.domain.repository.AuthRepository
import kr.hs.dgsw.woowasiblings.dgswchat.domain.repository.ChatRepository
import kr.hs.dgsw.woowasiblings.dgswchat.domain.repository.CommentRepository
import kr.hs.dgsw.woowasiblings.dgswchat.domain.repository.PostRepository
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
}