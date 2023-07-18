package com.u3f.githubusers.data.repository.profile

import android.provider.ContactsContract.Profile
import com.u3f.githubusers.data.remote.retrofit.AppRetrofitService
import com.u3f.githubusers.domain.model.profile.ProfileModel
import com.u3f.githubusers.domain.repository.profile.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
private val appRetrofitService: AppRetrofitService,
): ProfileRepository {
    override suspend fun getProfile(username:String): ProfileModel {
        return appRetrofitService.getProfile(username)
    }
}