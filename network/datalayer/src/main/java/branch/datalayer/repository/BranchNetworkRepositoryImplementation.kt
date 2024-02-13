package branch.datalayer.repository

import branch.datalayer.interceptor.BranchInterceptor
import branch.domainlayer.apiservice.BranchApiService
import branch.domainlayer.dto.BranchMessage
import branch.domainlayer.dto.LoginRequest
import branch.domainlayer.dto.LoginResponse
import branch.domainlayer.dto.MessageRequest
import branch.domainlayer.repository.BranchNetworkRepository
import retrofit2.Call
import timber.log.Timber
import javax.inject.Inject

class BranchNetworkRepositoryImplementation @Inject constructor(
    private val branchApiService: BranchApiService
) : BranchNetworkRepository {

    override suspend fun getMessages(): List<BranchMessage> {
        Timber.tag(tag = "List of Messages").d(message = branchApiService.getMessages().toString())
        return branchApiService.getMessages()
    }

    override suspend fun createMessage(
        messageThreadId: Int,
        messageBody: String
    ): BranchMessage {
        return branchApiService.createMessage(
            messageRequest = MessageRequest(
                messageThreadId = messageThreadId,
                messageBody = messageBody
            )
        )
    }

    override suspend fun login(username: String, password: String): LoginResponse? {

        val loginRequest = LoginRequest(
            username = username,
            password = password
        )

        return try {
            val response = branchApiService.login(loginRequest = loginRequest)
            response
        } catch (e: Exception) {
            null
        }

    }

    override suspend fun getMessagesByThread(threadId: Int): List<BranchMessage> {
        return branchApiService.getMessages().filter { it.messageThreadId == threadId }
    }

    override suspend fun reset() {
        branchApiService.reset()
    }

}