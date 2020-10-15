package com.childhealthcare.parent.di


import com.childhealthcare.parent.data.Api
import com.childhealthcare.parent.data.ApiRepository
import com.childhealthcare.parent.data.PrefRepository
import com.childhealthcare.parent.ui.account.AccountViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL = "http://vaccinsystem.gearhostpreview.com/"

val viewModelModule = module {

    viewModel { AccountViewModel(get(), get()) }
}

val repositoryModule = module {
    fun provideHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    fun createCustomerApi(factory: GsonConverterFactory, client: OkHttpClient) =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(factory)
            .client(client)
            .build()
            .create(Api::class.java)

    single { PrefRepository(androidApplication()) }
    single { ApiRepository(get()) }
    single { provideHttpClient() }
    single { GsonConverterFactory.create() }
    single { createCustomerApi(get(), get()) }
}
