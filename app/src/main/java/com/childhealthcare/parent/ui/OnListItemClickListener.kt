package com.childhealthcare.parent.ui

interface OnListItemClickListener <T>{
    fun onItemClick(item: T, pos: Int)
}