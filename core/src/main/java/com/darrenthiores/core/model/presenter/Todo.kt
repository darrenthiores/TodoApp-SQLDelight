package com.darrenthiores.core.model.presenter

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Todo(
    val id: Long = 0,
    val title: String,
    val description: String,
    val done: Boolean = false
): Parcelable
