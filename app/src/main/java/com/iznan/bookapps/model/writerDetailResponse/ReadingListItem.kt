package com.iznan.bookapps.model.writerDetailResponse

import com.google.gson.annotations.SerializedName

data class ReadingListItem(

	@field:SerializedName("Writer_by_writer_id")
	val writerByWriterId: WriterByWriterId? = null,

	@field:SerializedName("cover_url")
	val coverUrl: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("isNew")
	val isNew: Boolean? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("schedule_task")
	val scheduleTask: String? = null,

	@field:SerializedName("is_update")
	val isUpdate: Boolean? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("rate_sum")
	val rateSum: Double? = null,

	@field:SerializedName("writer_id")
	val writerId: Int? = null,

	@field:SerializedName("view_count")
	val viewCount: Int? = null,

	@field:SerializedName("chapter_count")
	val chapterCount: Int? = null,

	@field:SerializedName("status")
	val status: String? = null
)