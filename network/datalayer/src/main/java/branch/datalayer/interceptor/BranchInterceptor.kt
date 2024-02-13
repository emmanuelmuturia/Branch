package branch.datalayer.interceptor

import branch.network.datalayer.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class BranchInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader(name = BuildConfig.authHeader, value = BuildConfig.authToken)
            .build()
        return chain.proceed(request = request)
    }

}