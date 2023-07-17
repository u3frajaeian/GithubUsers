package com.u3f.githubusers.base.common

import com.u3f.githubusers.domain.model.error.NetworkError


sealed class Response<out T : Any> {
    data class Success<out T : Any>(val data: T) : Response<T>()
    data class Error(val error: NetworkError) : Response<Nothing>()
    object Loading : Response<Nothing>()
}