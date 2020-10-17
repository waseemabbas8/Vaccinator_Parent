package com.childhealthcare.parent.model


import com.google.gson.annotations.SerializedName

data class Polio(
    @SerializedName("ChildId")
    val childId: Int,
    @SerializedName("Date")
    val date: String,
    @SerializedName("Id")
    val id: Int,
    @SerializedName("Vaccinator")
    val vaccinator: String
)