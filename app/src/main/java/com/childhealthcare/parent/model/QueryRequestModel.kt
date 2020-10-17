package com.childhealthcare.parent.model


import com.google.gson.annotations.SerializedName

data class QueryRequestModel(
    @SerializedName("Message")
    val message: String,
    @SerializedName("Parent_Id")
    val parentId: Int,
    @SerializedName("Id")
    val id: Int = 0
)