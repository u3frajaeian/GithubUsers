package com.u3f.githubusers.domain.repository.favourite

import com.u3f.githubusers.domain.model.profile.ProfileModel

interface FavouriteRepository {
    suspend fun getAllFavUsers(): List<ProfileModel>


}