package com.example.blogexplorer.models

import androidx.annotation.Keep

@Keep
data class Company(
    val name: String,
    val catchPhrase: String,
    val br: String
)
