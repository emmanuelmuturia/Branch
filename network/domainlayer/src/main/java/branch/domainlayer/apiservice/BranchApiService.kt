package branch.domainlayer.apiservice

import branch.domainlayer.dto.BranchMessage
import branch.network.domainlayer.BuildConfig
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface BranchApiService {

    @GET(value = BuildConfig.messagesEndpoint)
    suspend fun getMessages(): List<BranchMessage>

    @POST(value = BuildConfig.messagesEndpoint)
    suspend fun createMessage(
        @Query(value = "thread_id") messageThreadId: Int,
        @Body messageBody: String
    ): Call<BranchMessage>

}