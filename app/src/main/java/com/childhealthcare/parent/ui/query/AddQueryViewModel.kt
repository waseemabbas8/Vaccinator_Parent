package com.childhealthcare.parent.ui.query

import android.view.View
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.childhealthcare.parent.data.ApiRepository
import com.childhealthcare.parent.data.MSG_INTERNET_FAILURE
import com.childhealthcare.parent.data.RESPONSE_CODE_ERROR
import com.childhealthcare.parent.model.QueryRequestModel
import com.childhealthcare.parent.model.common.GeneralResponse
import kotlinx.coroutines.launch
import java.io.IOException

class AddQueryViewModel(
    private val repository: ApiRepository,
    private val userId: Int
) : ViewModel() {

    val description = MutableLiveData<String>()

    val progressbarVisibility: LiveData<Int>
    private val _progressbarVisibility = MutableLiveData<Int>()

    val generalResponse: LiveData<GeneralResponse>
    private val _generalResponse = MutableLiveData<GeneralResponse>()

    init {
        progressbarVisibility = _progressbarVisibility
        _progressbarVisibility.value = View.GONE
        generalResponse = _generalResponse
    }

    @WorkerThread
    fun addQuery() {
        viewModelScope.launch {
            _generalResponse.postValue(null)
            _progressbarVisibility.value = View.VISIBLE
            try {
                if (description.value.isNullOrEmpty()){
                    _generalResponse.postValue(GeneralResponse(RESPONSE_CODE_ERROR, "Please add some description."))
                    return@launch
                }
                val query = QueryRequestModel(description.value!!, userId)
                val response = repository.submitQuery(query)
                if (response.isSuccessful){
                    response.body()?.let {
                        _generalResponse.postValue(it)
                    }
                }

            } catch (e: Exception) {
                val msg = if (e is IOException) MSG_INTERNET_FAILURE else e.message.toString()
                _generalResponse.postValue(GeneralResponse(RESPONSE_CODE_ERROR, msg))
            } catch (t: Throwable) {
                _generalResponse.postValue(GeneralResponse(RESPONSE_CODE_ERROR, t.message.toString()))
            }
            _progressbarVisibility.value = View.GONE
        }
    }

}