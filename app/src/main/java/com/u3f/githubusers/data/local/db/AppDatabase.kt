package com.u3f.githubusers.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.u3f.githubusers.data.local.dao.users.UsersDao
import com.u3f.githubusers.domain.model.profile.ProfileModel

@Database(
    entities = [ProfileModel::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favUserDao(): UsersDao
}