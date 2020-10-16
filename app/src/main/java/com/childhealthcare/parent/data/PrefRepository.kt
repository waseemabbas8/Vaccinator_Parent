package com.childhealthcare.parent.data

import android.app.Application
import android.content.Context
import com.childhealthcare.parent.R
import com.childhealthcare.parent.model.GridMenu
import com.childhealthcare.parent.model.User
import com.google.gson.Gson
import java.lang.Exception

private const val KEY_USER_OBJ = "parent_user_object"

class PrefRepository(private val app: Application){

    private val pref = app.getSharedPreferences(APP_TAG, Context.MODE_PRIVATE)


    fun getDashboardItems() : List<GridMenu> = listOf(
        GridMenu(app.getString(R.string.personal),R.drawable.ic_user_avatar),
        GridMenu(app.getString(R.string.chldren),R.drawable.ic_children),
        GridMenu(app.getString(R.string.query),R.drawable.ic_query)
    )

    fun saveUser(user: User) {
        val userStr = Gson().toJson(user)
        pref.edit().putString(KEY_USER_OBJ, userStr).apply()
    }

    fun getUser(): User? {
        val userJson = pref.getString(KEY_USER_OBJ, null)
        return try {
            Gson().fromJson(userJson, User::class.java)
        } catch (e: Exception) {
            null
        }
    }

    fun deleteUser() {
        pref.edit().remove(KEY_USER_OBJ).apply()
    }

}