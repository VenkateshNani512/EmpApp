package com.venky.empinfo.di

import android.content.Context
import com.venky.empinfo.data.local.TokenManager
import com.venky.empinfo.data.remote.AuthInterceptor
import com.venky.empinfo.data.remote.EmployeeApi
import com.venky.empinfo.data.remote.api.AuthApi
import com.venky.empinfo.data.repository.AuthRepositoryImpl
import com.venky.empinfo.data.repository.EmployeeRepositoryImpl
import com.venky.empinfo.domain.repository.AuthRepository
import com.venky.empinfo.domain.repository.EmployeeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "http://192.168.0.118:8080/"

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Provides
    @Singleton
    fun provideEmployeeApi(retrofit: Retrofit): EmployeeApi{
        return retrofit.create(EmployeeApi::class.java)
    }
    @Provides
    @Singleton
    fun provideOkhttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }
    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }
    @Provides
    @Singleton
    fun provideAuthRepository(authApi: AuthApi): AuthRepository{
        return AuthRepositoryImpl(authApi)
    }

    @Provides
    @Singleton
    fun provideTokenManager(@ApplicationContext context: Context): TokenManager{
        return TokenManager(context)
    }

    @Provides
    @Singleton
    fun provideEmployeeRepository(
        employeeApi: EmployeeApi
    ): EmployeeRepository {
        return EmployeeRepositoryImpl(employeeApi)
    }

    }

