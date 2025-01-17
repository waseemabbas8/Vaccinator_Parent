package com.childhealthcare.parent.data

import com.childhealthcare.parent.model.*
import com.childhealthcare.parent.model.common.BaseResponse
import com.childhealthcare.parent.model.common.GeneralResponse
import com.childhealthcare.parent.model.common.SpinnerItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface Api {

    @POST("SignInParent")
    suspend fun login(
        @Query("cnic") cnic: String,
        @Query("password") password: String
    ): Response<BaseResponse<User>>

    @POST("SignupParent")
    suspend fun signUp(
        @Body user: UserSignUpModel
    ): Response<BaseResponse<User>>

    @GET("GetUcList")
    suspend fun getAllUcs(): Response<List<SpinnerItem>>

    @GET("GetMuhallasListByUcId")
    suspend fun getMohallasByUcId(
        @Query("ucid") ucId: Int
    ): Response<List<SpinnerItem>>

    @GET("GetChildListByCNIC")
    suspend fun getChildren(
        @Query("cnic") cnic: String
    ): Response<List<Child>>

    @GET("GetSingleChildDetail")
    suspend fun getChildDetails(
        @Query("id") childId: Int
    ): Response<Child>

    @GET("ComplaintListByParentID")
    suspend fun getQueriesByParentId(
        @Query("pid") parentId: Int
    ): Response<BaseResponse<List<QueryModel>>>

    @POST("SubmitComplaint")
    suspend fun submitQuery(
        @Body query: QueryRequestModel
    ): Response<GeneralResponse>

//
//    @GET("GetChildListByMuhalla")
//    suspend fun getChildrenList(
//        @Query("ucid") ucId: Int,
//        @Query("mid") mohId: Int
//    ): Response<List<Child>>
//
//    @GET("GetMuhallasListByUcId")
//    suspend fun getMohallahs(
//        @Query("ucid") ucId: Int
//    ): Response<List<Mohallah>>
//
//    @GET("GetSingleChildDetail")
//    suspend fun getChildDetails(
//        @Query("id") childId: Int
//    ): Response<Child>
//
//    @POST("AddVaccination")
//    suspend fun addVaccination(
//        @Query("cid") childId: Int,
//        @Query("vid") vaccinatorId: Int
//    ): Response<GeneralResponse>
//
//    @GET("GetVaccinatorTaskList")
//    suspend fun getTasksList(
//        @Query("vid") vaccinatorId: Int
//    ): Response<BaseResponse<List<TodoTask>>>
//
//    @POST("AddVaccinatorTask")
//    suspend fun addVaccinatorTask(
//        @Body task: TodoTask
//    ): Response<GeneralResponse>
//

}