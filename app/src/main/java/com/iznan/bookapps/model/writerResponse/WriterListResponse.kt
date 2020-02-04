package com.iznan.bookapps.model.writerResponse

import com.google.gson.annotations.SerializedName

data class WriterListResponse(

	@field:SerializedName("result")
	val result: List<ResultItem?>? = null,

	@field:SerializedName("success")
	val success: Boolean? = null
)