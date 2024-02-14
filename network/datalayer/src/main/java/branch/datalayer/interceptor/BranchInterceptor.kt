package branch.datalayer.interceptor

import android.content.Context
import branch.datalayer.sessionmanager.SessionManager
import branch.network.datalayer.BuildConfig
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class BranchInterceptor(context: Context) : Interceptor {

    private val sessionManager: SessionManager = SessionManager(context = context)

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        sessionManager.fetchToken()?.let { authToken ->
            requestBuilder.addHeader(name = BuildConfig.authHeader, value = authToken)
        }
        return chain.proceed(request = requestBuilder.build())
    }

}