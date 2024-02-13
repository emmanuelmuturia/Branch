package branch.domainlayer.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BranchMessage(
    @SerialName(value = "id")
    val messageId: Int,
    @SerialName(value = "thread_id")
    val messageThreadId: Int,
    @SerialName(value = "user_id")
    val messageUserId: Int,
    @SerialName(value = "agent_id")
    val messageAgentId: Int?,
    @SerialName(value = "body")
    val messageBody: String,
    @SerialName(value = "timestamp")
    val messageTimestamp: String
)
