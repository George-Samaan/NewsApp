package com.route.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Source(

    val country: String? = null,

    val name: String? = null,

    val description: String? = null,

    val id: String? = null,

    val category: String? = null,

    ) : Parcelable