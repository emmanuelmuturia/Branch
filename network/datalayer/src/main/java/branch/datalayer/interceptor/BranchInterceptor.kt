package branch.datalayer.interceptor

import branch.datalayer.repository.BranchNetworkRepositoryImplementation
import branch.domainlayer.repository.BranchNetworkRepository
import branch.network.datalayer.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

class BranchInterceptor: Interceptor {

    private var authToken: String? = null

    fun setAuthToken(token: String?) {
        authToken = token
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader(name = BuildConfig.authHeader, value = "SdRUS8v5kdVRm3kllfS3Cw")
            .build()
        Timber.tag(tag = "The Auth Token").d(message = authToken)
        return chain.proceed(request = request)
    }

}