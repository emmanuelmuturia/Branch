package branch.datalayer.repository

import branch.domainlayer.apiservice.BranchApiService
import branch.domainlayer.dto.BranchMessage
import branch.domainlayer.repository.BranchNetworkRepository
import retrofit2.Call
import javax.inject.Inject

class BranchNetworkRepositoryImplementation @Inject constructor(
    private val branchApiService: BranchApiService
) : BranchNetworkRepository {

    override suspend fun getMessages(): List<BranchMessage> {
        return branchApiService.getMessages()
    }

    override suspend fun createMessage(messageThreadId: Int, messageBody: String): Call<BranchMessage> {
        return branchApiService.createMessage(
            messageThreadId = messageThreadId,
            messageBody = messageBody
        )
    }

}