package com.u3f.githubusers.domain.repository.search

import com.u3f.githubusers.domain.model.search.UserDataClass


interface SearchRepository {
    suspend fun getUsers(username:String): UserDataClass

}