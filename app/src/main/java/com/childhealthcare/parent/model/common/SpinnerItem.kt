package com.childhealthcare.parent.model.common

data class SpinnerItem(
    val id: Int,
    val name: String
) {
    override fun toString(): String = name
}