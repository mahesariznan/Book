package com.iznan.bookapps.model.genreResponse

import com.google.gson.annotations.SerializedName

data class GenreResponse(

	@field:SerializedName("resource")
	val resource: List<ResourceItem?>? = null
)