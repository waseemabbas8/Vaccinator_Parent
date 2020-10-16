package com.childhealthcare.parent.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(
    @SerializedName("CNIC")
    val cNIC: String,
    @SerializedName("Id")
    val id: Int,
    @SerializedName("MUhalla")
    val mohName: String,
    @SerializedName("Mobile")
    val mobile: String,
    @SerializedName("Name")
    val name: String,
    @SerializedName("UC")
    val ucName: String
): Serializable