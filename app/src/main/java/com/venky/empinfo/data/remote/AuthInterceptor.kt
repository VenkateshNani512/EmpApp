package com.venky.empinfo.data.remote

import com.venky.empinfo.data.local.TokenManager
import jakarta.inject.Inject
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response


class AuthInterceptor @Inject constructor(private val tokenManager: TokenManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()

        // Don't send JWT for login/register
        if (request.url.encodedPath.startsWith("/auth/")) {
            return chain.proceed(request)
        }

        val token = runBlocking {
            tokenManager.getToken().first()
        }

        val newRequest = request.newBuilder()

        if (!token.isNullOrBlank()) {
            newRequest.addHeader(
                "Authorization",
                "Bearer $token"
            )
        }
        return chain.proceed(newRequest.build())
    }

    }

