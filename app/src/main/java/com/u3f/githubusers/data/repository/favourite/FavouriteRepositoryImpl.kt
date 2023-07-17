package com.u3f.githubusers.data.repository.favourite

import com.u3f.githubusers.data.local.dao.users.UsersDao
import com.u3f.githubusers.domain.model.profile.ProfileModel
import com.u3f.githubusers.domain.repository.favourite.FavouriteRepository
import javax.inject.Inject

class FavouriteRepositoryImpl @Inject constructor(
    private val reposDao: UsersDao
) : FavouriteRepository {
    override suspend fun getAllFavUsers(): List<ProfileModel> {
        return reposDao.getAllFavUsers()
    }


}