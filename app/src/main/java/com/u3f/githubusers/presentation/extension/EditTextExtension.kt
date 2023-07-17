package com.u3f.githubusers.presentation.extension

import android.widget.EditText

fun EditText.getInputText(): String {
    return this.text.toString()
}