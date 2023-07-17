package com.u3f.githubusers.domain.repository.users

import com.u3f.githubusers.domain.model.users.FollowerDataClass


interface UsersRepository {
    suspend fun getFollowers(username:String): List<FollowerDataClass>
    suspend fun getFollowing(username:String): List<FollowerDataClass>

}