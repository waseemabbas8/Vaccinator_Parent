package com.childhealthcare.parent.ui.child

import android.view.View
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.childhealthcare.parent.data.ApiRepository
import com.childhealthcare.parent.data.RESPONSE_CODE_ERROR
import com.childhealthcare.parent.model.Child
import com.childhealthcare.parent.model.common.GeneralResponse
import com.childhealthcare.parent.ui.common.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChildViewModel(
    private val apiRepository: ApiRepository,
    private val childId: Int
) : BaseViewModel() {

    val child: LiveData<Child>
    private val _child = MutableLiveData<Child>()

    init {
        child = _child
        getChildDetails()
    }

    @WorkerThread
    private fun getChildDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            _progressbarVisibility.postValue(View.VISIBLE)
            try {
                val response = apiRepository.getChildDetails(childId)
                if (response.isSuccessful) {
                    response.body()?.let {
                        _child.postValue(it)
                    }
                }
            } catch (e: Exception) {
                _generalResponse.postValue(GeneralResponse(RESPONSE_CODE_ERROR, e.message ?: "ERROR"))
            } catch (t: Throwable) {
                _generalResponse.postValue(GeneralResponse(RESPONSE_CODE_ERROR, t.message ?: "ERROR"))
            }
            _progressbarVisibility.postValue(View.GONE)
        }
    }

}