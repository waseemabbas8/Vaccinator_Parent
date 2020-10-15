package com.childhealthcare.parent.data

import com.childhealthcare.parent.model.User
import com.childhealthcare.parent.model.UserSignUpModel


class ApiRepository(private val api: Api) {

    suspend fun login(userName: String, password: String) = api.login(userName, password)

    suspend fun signUp(user: UserSignUpModel) = api.signUp(user)

    suspend fun getAllUcs()  = api.getAllUcs()

    suspend fun getMohallasByUcId(ucId: Int) = api.getMohallasByUcId(ucId)
}