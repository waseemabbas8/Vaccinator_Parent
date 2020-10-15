package com.childhealthcare.parent.ui.common

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.childhealthcare.parent.model.common.GeneralResponse

abstract class BaseViewModel : ViewModel() {

    val progressbarVisibility: LiveData<Int>
    protected val _progressbarVisibility = MutableLiveData<Int>()

    val generalResponse: LiveData<GeneralResponse>
    protected val _generalResponse = MutableLiveData<GeneralResponse>()

    init {
        progressbarVisibility = _progressbarVisibility
        _progressbarVisibility.value = View.GONE
        generalResponse = _generalResponse
    }
}