package com.u3f.githubusers.presentation.search

import com.u3f.githubusers.domain.model.error.NetworkError
import com.u3f.githubusers.domain.model.search.UserDataClass

sealed class SearchState {
    class UserFetched(val users: UserDataClass) : SearchState()
    class Error(val error: NetworkError) : SearchState()
    object Loading : SearchState()
}
