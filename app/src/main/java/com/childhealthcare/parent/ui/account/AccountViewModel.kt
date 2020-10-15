package com.childhealthcare.parent.ui.account

import android.view.View
import android.widget.Toast
import androidx.annotation.WorkerThread
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.ilhasoft.support.validation.Validator
import com.childhealthcare.parent.data.ApiRepository
import com.childhealthcare.parent.data.PrefRepository
import com.childhealthcare.parent.data.RESPONSE_CODE_ERROR
import com.childhealthcare.parent.data.RESPONSE_CODE_OK
import com.childhealthcare.parent.model.UserSignUpModel
import com.childhealthcare.parent.model.common.GeneralResponse
import com.childhealthcare.parent.model.common.SpinnerItem
import com.childhealthcare.parent.ui.common.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AccountViewModel(
    private val apiRepository: ApiRepository,
    private val prefRepository: PrefRepository
) : BaseViewModel() {

    val userName = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    val name by lazy { MutableLiveData<String>() }
    val phone by lazy { MutableLiveData<String>() }
    val confirmPassword by lazy { MutableLiveData<String>() }
    private val _mohallas by lazy { MutableLiveData<List<SpinnerItem>>() }
    val mohallas by lazy {
        _mohallas.value = listOf(SpinnerItem(0, "Select Mohallah"))
        _mohallas
    }
    private val _unionCouncils by lazy { MutableLiveData<List<SpinnerItem>>() }
    val unionCouncils by lazy {
        _unionCouncils.value = listOf(SpinnerItem(0, "Select Union Council"))
        _unionCouncils
    }

    var ucId: Int = 0
    var mohId = 0


    private lateinit var validator: Validator

    fun setValidator(binding: ViewDataBinding) {
        validator = Validator(binding)
    }

    @WorkerThread
    fun login() {
        if (!validator.validate()) return
        viewModelScope.launch(Dispatchers.IO) {
            _progressbarVisibility.postValue(View.VISIBLE)
            try {
                val response = apiRepository.login(userName.value ?: "", password.value ?: "")
                if (response.isSuccessful) {
                    response.body()?.let {
                        if (it.code == RESPONSE_CODE_OK)
                            prefRepository.saveUser(it.data)
                        _generalResponse.postValue(GeneralResponse(it.code, it.message))
                    }
                }
            } catch (e: Exception) {
                _generalResponse.postValue(
                    GeneralResponse(
                        RESPONSE_CODE_ERROR,
                        e.message.toString()
                    )
                )
            } catch (t: Throwable) {
                _generalResponse.postValue(
                    GeneralResponse(
                        RESPONSE_CODE_ERROR,
                        t.message.toString()
                    )
                )
            }
            _progressbarVisibility.postValue(View.GONE)
        }
    }

    @WorkerThread
    fun signUp(view: View) {
        if (!validator.validate()) return
        if (ucId == 0 || mohId == 0) {
            Toast.makeText(view.context, "Please select your UC and Mohallah", Toast.LENGTH_SHORT)
                .show()
            return
        }
        if (confirmPassword.value != password.value) {
            Toast.makeText(view.context, "Passwords must match", Toast.LENGTH_SHORT).show()
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            _progressbarVisibility.postValue(View.VISIBLE)
            try {
                val user = UserSignUpModel(
                    userName.value ?: "",
                    mohId, phone.value ?: "", name.value ?: "", password.value ?: "",
                    ucId
                )
                val response = apiRepository.signUp(user)
                if (response.isSuccessful) {
                    response.body()?.let {
                        if (it.code == RESPONSE_CODE_OK)
                            prefRepository.saveUser(it.data)
                        _generalResponse.postValue(GeneralResponse(it.code, it.message))
                    }
                }
            } catch (e: Exception) {
                _generalResponse.postValue(
                    GeneralResponse(
                        RESPONSE_CODE_ERROR,
                        e.message.toString()
                    )
                )
            } catch (t: Throwable) {
                _generalResponse.postValue(
                    GeneralResponse(
                        RESPONSE_CODE_ERROR,
                        t.message.toString()
                    )
                )
            }
            _progressbarVisibility.postValue(View.GONE)
        }
    }

    fun getAllUcs() {
        viewModelScope.launch(Dispatchers.IO) {
            _progressbarVisibility.postValue(View.VISIBLE)
            try {
                val response = apiRepository.getAllUcs()
                if (response.isSuccessful) {
                    response.body()?.let {
                        val temp = arrayListOf<SpinnerItem>()
                        temp.add(SpinnerItem(0, "Select Union Council"))
                        temp.addAll(it)
                        _unionCouncils.postValue(temp)
                    }
                }
            } catch (e: Exception) {
                _generalResponse.postValue(
                    GeneralResponse(
                        RESPONSE_CODE_ERROR,
                        e.message.toString()
                    )
                )
            } catch (t: Throwable) {
                _generalResponse.postValue(
                    GeneralResponse(
                        RESPONSE_CODE_ERROR,
                        t.message.toString()
                    )
                )
            }
            _progressbarVisibility.postValue(View.GONE)
        }
    }

    fun getMohallas() {
        if (ucId == 0) return
        viewModelScope.launch(Dispatchers.IO) {
            _progressbarVisibility.postValue(View.VISIBLE)
            try {
                val response = apiRepository.getMohallasByUcId(ucId)
                if (response.isSuccessful) {
                    response.body()?.let {
                        val temp = arrayListOf<SpinnerItem>()
                        temp.add(SpinnerItem(0, "Select Mohallah"))
                        temp.addAll(it)
                        _mohallas.postValue(temp)
                    }
                }
            } catch (e: Exception) {
                _generalResponse.postValue(
                    GeneralResponse(
                        RESPONSE_CODE_ERROR,
                        e.message.toString()
                    )
                )
            } catch (t: Throwable) {
                _generalResponse.postValue(
                    GeneralResponse(
                        RESPONSE_CODE_ERROR,
                        t.message.toString()
                    )
                )
            }
            _progressbarVisibility.postValue(View.GONE)
        }
    }

}