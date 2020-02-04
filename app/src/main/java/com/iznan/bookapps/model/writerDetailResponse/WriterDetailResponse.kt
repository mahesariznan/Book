package com.iznan.bookapps.model.writerDetailResponse

import com.google.gson.annotations.SerializedName

data class WriterDetailResponse(

	@field:SerializedName("result")
	val result: Result? = null,

	@field:SerializedName("success")
	val success: Boolean? = null
)