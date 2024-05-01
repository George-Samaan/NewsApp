package com.route.data.api.model.sourcesResponse

import com.route.domain.model.Source

data class SourceDto(

    @field:com.google.gson.annotations.SerializedName("country")
    val country: String? = null,

    @field:com.google.gson.annotations.SerializedName("name")
    val name: String? = null,

    @field:com.google.gson.annotations.SerializedName("description")
    val description: String? = null,

    @field:com.google.gson.annotations.SerializedName("language")
    val language: String? = null,

    @field:com.google.gson.annotations.SerializedName("id")
    val id: String? = null,

    @field:com.google.gson.annotations.SerializedName("category")
    val category: String? = null,

    @field:com.google.gson.annotations.SerializedName("url")
    val url: String? = null
) {
    fun toSource(): Source {

        return Source(
            country, name, description, id, category,
        )
    }
}