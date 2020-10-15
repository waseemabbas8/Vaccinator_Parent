package com.childhealthcare.parent.ui.child

import android.view.View
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.childhealthcare.parent.data.ApiRepository
import com.childhealthcare.parent.model.Child
import com.childhealthcare.parent.ui.common.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChildrenListViewModel(
    private val repository: ApiRepository,
    private val cNic: String,
) : BaseViewModel() {

    val children: LiveData<List<Child>>
    private val _children = MutableLiveData<List<Child>>()

    init {
        children = _children
        _children.value = emptyList()

        getChildrenList()
    }

    @WorkerThread
    fun getChildrenList() {
        viewModelScope.launch(Dispatchers.IO) {
            _progressbarVisibility.postValue(View.VISIBLE)
            try {
                val response = repository.getChildren(cNic)
                if (response.isSuccessful) {
                    response.body()?.let {
                        _children.postValue(it)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } catch (t: Throwable) {
                t.printStackTrace()
            }
            _progressbarVisibility.postValue(View.GONE)
        }
    }

}