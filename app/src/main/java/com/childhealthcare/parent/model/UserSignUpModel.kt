package com.childhealthcare.parent.model


import com.google.gson.annotations.SerializedName

data class UserSignUpModel(
    @SerializedName("CNIC")
    val cNIC: String,
    @SerializedName("MID")
    val mID: Int,
    @SerializedName("Mobile")
    val mobile: String,
    @SerializedName("Name")
    val name: String,
    @SerializedName("Password")
    val password: String,
    @SerializedName("UCID")
    val uCID: Int,
    @SerializedName("Id")
    private val id: Int = 0,
)