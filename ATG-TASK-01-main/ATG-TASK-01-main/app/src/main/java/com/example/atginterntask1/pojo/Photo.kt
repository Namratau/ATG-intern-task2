package com.example.atginterntask1.pojo

import com.google.gson.annotations.SerializedName

data class Photo (
	@SerializedName("id") val id : String,
	@SerializedName("owner") val owner : String,
	@SerializedName("secret") val secret : String,
	@SerializedName("server") val server : Int,
	@SerializedName("farm") val farm : Int,
	@SerializedName("title") val title : String,
	@SerializedName("ispublic") val ispublic : Int,
	@SerializedName("isfriend") val isfriend : Int,
	@SerializedName("isfamily") val isfamily : Int,
	@SerializedName("url_s") val url_s : String,
	@SerializedName("height_s") val height_s : Int,
	@SerializedName("width_s") val width_s : Int
)