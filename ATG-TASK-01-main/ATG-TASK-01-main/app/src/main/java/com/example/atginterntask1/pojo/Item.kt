package com.example.atginterntask1.pojo

import com.google.gson.annotations.SerializedName

data class Item (
	@SerializedName("photos") val photos : Photos,
	@SerializedName("stat") val stat : String
)