package com.u3f.githubusers.di

import android.content.Context
import androidx.room.Room
import com.u3f.githubusers.data.remote.retrofit.AppRetrofitService
import com.u3f.githubusers.data.repository.profile.ProfileRepositoryImpl
import com.u3f.githubusers.data.repository.search.SearchRepositoryImpl
import com.u3f.githubusers.data.repository.users.UsersRepositoryImpl
import com.u3f.githubusers.domain.repository.profile.ProfileRepository
import com.u3f.githubusers.domain.repository.search.SearchRepository
import com.u3f.githubusers.domain.repository.users.UsersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.u3f.githubusers.presentation.navigation.NavManager
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideProfileRepository(apiService: AppRetrofitService): ProfileRepository {
        return ProfileRepositoryImpl(apiService)
    }


    @Provides
    @Singleton
    fun provideSearchRepository(apiService: AppRetrofitService): SearchRepository {
        return SearchRepositoryImpl(apiService)
    }
    @Provides
    @Singleton
    fun provideUsersRepository(apiService: AppRetrofitService): UsersRepository {
        return UsersRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideNavManager(): NavManager {
        return NavManager()
    }

}