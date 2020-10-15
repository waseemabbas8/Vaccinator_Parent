package com.childhealthcare.parent.ui.home

import androidx.lifecycle.ViewModel
import com.childhealthcare.parent.data.PrefRepository

class DashboardViewModel(private val prefRepository: PrefRepository) : ViewModel() {

    val dashboardItems = prefRepository.getDashboardItems()

    val user = prefRepository.getUser()

    fun logoutUser() {
        prefRepository.deleteUser()
    }

}