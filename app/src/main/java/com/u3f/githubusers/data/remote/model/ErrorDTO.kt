package com.u3f.githubusers.data.remote.model


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.u3f.githubusers.domain.model.error.NetworkError

@Keep
data class ErrorDTO(
    val messages: String,
    @SerializedName("data")
    val `data`: Any?,
    val hasError: Boolean
)

fun ErrorDTO.toError() = NetworkError(
    message = messages
)