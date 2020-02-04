package com.iznan.bookapps.model.bookFromGenreResponse

import com.google.gson.annotations.SerializedName

data class BookFromGenreResponse(

	@field:SerializedName("result")
	val result: List<ResultItem?>? = null,

	@field:SerializedName("test")
	val test: Test? = null,

	@field:SerializedName("success")
	val success: Boolean? = null
)