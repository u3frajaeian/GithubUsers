package com.u3f.githubusers.data.repository.search

import com.u3f.githubusers.data.remote.retrofit.AppRetrofitService
import com.u3f.githubusers.domain.model.search.UserDataClass
import com.u3f.githubusers.domain.repository.search.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val appRetrofitService: AppRetrofitService,
) : SearchRepository {
    override suspend fun getUsers(username: String): UserDataClass {
        return appRetrofitService.getUsers(username)
    }

}