package com.RipeChecker.RiCek

import com.google.gson.annotations.SerializedName

data class Response(

	@field:SerializedName("confidence")
	val confidence: String? = null,

	@field:SerializedName("predicted_class")
	val predictedClass: String? = null
)
