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
import com.childhealthcare.parent.model.QueryModel
import com.childhealthcare.parent.model.common.GeneralResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

class QueriesViewModel(
    private val repository: ApiRepository,
    private val userId: Int
) : ViewModel() {

    val queries: LiveData<List<QueryModel>>
    private val _queries = MutableLiveData<List<QueryModel>>()

    val progressbarVisibility: LiveData<Int>
    private val _progressbarVisibility = MutableLiveData<Int>()

    val generalResponse: LiveData<GeneralResponse>
    private val _generalResponse = MutableLiveData<GeneralResponse>()

    init {
        queries = _queries
        progressbarVisibility = _progressbarVisibility
        generalResponse = _generalResponse
    }

    fun getQueriesList() {
        viewModelScope.launch(Dispatchers.IO) {
            _progressbarVisibility.postValue(View.VISIBLE)
            try {
                val response = repository.getQueriesByParentId(userId)
                if (response.isSuccessful) {
                    response.body()?.let {
                        _queries.postValue(it.data)
                    }
                }
            } catch (e: Exception) {
                val msg = if (e is IOException) MSG_INTERNET_FAILURE else e.message.toString()
                _generalResponse.postValue(GeneralResponse(RESPONSE_CODE_ERROR, msg))
            } catch (t: Throwable) {
                _generalResponse.postValue(GeneralResponse(RESPONSE_CODE_ERROR, t.message ?: "ERROR"))
            }
            _progressbarVisibility.postValue(View.GONE)
        }
    }

}