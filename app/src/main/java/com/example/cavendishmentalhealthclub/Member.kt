package com.example.cavendishmentalhealthclub

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Data class to hold member registration information.
 * Implements Parcelable to allow passing through Intent.
 */
@Parcelize
data class Member(
    val name: String,
    val age: Int,
    val preferredProgram: String
) : Parcelable






