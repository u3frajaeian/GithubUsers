package com.u3f.githubusers.di

import android.content.Context
import androidx.room.Room
import com.u3f.githubusers.data.local.dao.users.UsersDao
import com.u3f.githubusers.data.local.db.AppDatabase
import com.u3f.githubusers.data.remote.retrofit.AppRetrofitService
import com.u3f.githubusers.data.repository.favourite.FavouriteRepositoryImpl
import com.u3f.githubusers.data.repository.profile.ProfileRepositoryImpl
import com.u3f.githubusers.data.repository.search.SearchRepositoryImpl
import com.u3f.githubusers.data.repository.users.UsersRepositoryImpl
import com.u3f.githubusers.domain.repository.favourite.FavouriteRepository
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
    fun provideProfileRepository(apiService: AppRetrofitService, userDao: UsersDao): ProfileRepository {
        return ProfileRepositoryImpl(apiService,userDao)
    }


    @Provides
    @Singleton
    fun provideFavsRepository(usersDao: UsersDao): FavouriteRepository {
        return FavouriteRepositoryImpl(usersDao)
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

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "git-db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserDao(db: AppDatabase): UsersDao {
        return db.favUserDao()
    }
}