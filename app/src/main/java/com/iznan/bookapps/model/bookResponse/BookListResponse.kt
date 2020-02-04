package com.iznan.bookapps.model.bookResponse

import com.google.gson.annotations.SerializedName

data class BookListResponse(

	@field:SerializedName("result")
	val result: List<ResultItem?>? = null,

	@field:SerializedName("success")
	val success: Boolean? = null
)