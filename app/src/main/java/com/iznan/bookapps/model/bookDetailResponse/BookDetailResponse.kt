package com.iznan.bookapps.model.bookDetailResponse

import com.google.gson.annotations.SerializedName

data class BookDetailResponse(

	@field:SerializedName("result")
	val result: Result? = null,

	@field:SerializedName("success")
	val success: Boolean? = null
)