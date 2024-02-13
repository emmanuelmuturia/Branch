package branch.domainlayer.repository

import branch.domainlayer.BranchState
import branch.domainlayer.dto.BranchMessage
import branch.domainlayer.dto.LoginResponse
import retrofit2.Call

interface BranchNetworkRepository {

    suspend fun getMessages(): List<BranchMessage>

    suspend fun createMessage(messageThreadId: Int, messageBody: String): Call<BranchMessage>

    suspend fun login(username: String, password: String): LoginResponse

}