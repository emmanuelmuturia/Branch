package branch.datalayer.repository

import branch.datalayer.interceptor.BranchInterceptor
import branch.domainlayer.apiservice.BranchApiService
import branch.domainlayer.dto.BranchMessage
import branch.domainlayer.dto.LoginRequest
import branch.domainlayer.dto.LoginResponse
import branch.domainlayer.repository.BranchNetworkRepository
import retrofit2.Call
import timber.log.Timber
import javax.inject.Inject

class BranchNetworkRepositoryImplementation @Inject constructor(
    private val branchApiService: BranchApiService,
    private val branchInterceptor: BranchInterceptor
) : BranchNetworkRepository {

    override suspend fun getMessages(): List<BranchMessage> {
        Timber.tag(tag = "List of Messages").d(message = branchApiService.getMessages().toString())
        return branchApiService.getMessages()
    }

    override suspend fun createMessage(
        messageThreadId: Int,
        messageBody: String
    ): Call<BranchMessage> {
        return branchApiService.createMessage(
            messageThreadId = messageThreadId,
            messageBody = messageBody
        )
    }

    override suspend fun login(username: String, password: String): LoginResponse? {

        val loginRequest = LoginRequest(
            username = username,
            password = password
        )

        return try {
            val response = branchApiService.login(loginRequest = loginRequest)
            branchInterceptor.setAuthToken(token = response.authToken)
            Timber.tag(tag = "Response Auth Token").d(message = response.authToken)
            response
        } catch (e: Exception) {
            null
        }

    }

}