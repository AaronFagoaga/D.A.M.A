package com.jfagoaga.puellalearn.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Animal(
    val name: String,
    val pronunciation: String,
    val imageResId: Int,
    val audioResId: Int
): Parcelable