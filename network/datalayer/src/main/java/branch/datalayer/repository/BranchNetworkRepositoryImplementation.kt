package branch.datalayer.repository

import android.content.Context
import branch.datalayer.sessionmanager.SessionManager
import branch.domainlayer.apiservice.BranchApiService
import branch.domainlayer.dto.BranchMessage
import branch.domainlayer.dto.LoginRequest
import branch.domainlayer.dto.LoginResponse
import branch.domainlayer.dto.MessageRequest
import branch.domainlayer.repository.BranchNetworkRepository
import timber.log.Timber
import javax.inject.Inject

class BranchNetworkRepositoryImplementation @Inject constructor(
    private val branchApiService: BranchApiService,
    context: Context
) : BranchNetworkRepository {

    private val sessionManager: SessionManager = SessionManager(context = context)

    override suspend fun getMessages(): List<BranchMessage> {
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
            sessionManager.saveAuthToken(token = response.authToken)
            Timber.tag(tag = "The Token:").d(message = "${sessionManager.fetchToken()}")
            response
        } catch (e: Exception) {
            null
        }

    }

    override suspend fun getMessagesByThread(threadId: Int): List<BranchMessage> {
        return branchApiService.getMessages().filter { it.messageThreadId == threadId }
    }

    override suspend fun resetMessages() {
        branchApiService.resetMessages()
    }

}