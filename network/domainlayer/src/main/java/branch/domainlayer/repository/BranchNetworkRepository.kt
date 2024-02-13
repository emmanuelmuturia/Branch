package branch.domainlayer.repository

import branch.domainlayer.dto.BranchMessage
import retrofit2.Call

interface BranchNetworkRepository {

    suspend fun getMessages(): List<BranchMessage>

    suspend fun createMessage(messageThreadId: Int, messageBody: String): Call<BranchMessage>

}