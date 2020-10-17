package com.childhealthcare.parent.model


import com.google.gson.annotations.SerializedName

data class QueryModel(
    @SerializedName("Id")
    val id: Int,
    @SerializedName("Message")
    val message: String,
    @SerializedName("Parent_Id")
    val parentId: Int,
    @SerializedName("Date")
    val date: String
)