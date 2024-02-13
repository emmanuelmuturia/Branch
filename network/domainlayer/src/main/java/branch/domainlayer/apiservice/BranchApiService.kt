package branch.domainlayer.apiservice

import branch.domainlayer.dto.BranchMessage
import branch.domainlayer.dto.LoginRequest
import branch.domainlayer.dto.LoginResponse
import branch.domainlayer.dto.MessageRequest
import branch.network.domainlayer.BuildConfig
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface BranchApiService {

    @POST(value = BuildConfig.loginEndpoint)
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): LoginResponse

    @GET(value = BuildConfig.messagesEndpoint)
    suspend fun getMessages(): List<BranchMessage>

    @POST(value = BuildConfig.messagesEndpoint)
    suspend fun createMessage(
        @Body messageRequest: MessageRequest
    ): BranchMessage

    @GET(value = BuildConfig.messagesEndpoint)
    suspend fun getMessageByThread(@Query(value = "thread_id") threadId: Int): List<BranchMessage>

}