package com.u3f.githubusers.domain.repository.profile

import com.u3f.githubusers.domain.model.profile.ProfileModel


interface ProfileRepository {
    suspend fun getProfile(username:String): ProfileModel
}