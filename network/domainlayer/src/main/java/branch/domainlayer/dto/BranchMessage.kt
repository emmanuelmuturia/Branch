package branch.domainlayer.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BranchMessage(
    @SerialName(value = "id")
    val messageId: Int = 0,
    @SerialName(value = "thread_id")
    val messageThreadId: Int = 0,
    @SerialName(value = "user_id")
    val messageUserId: String = "",
    @SerialName(value = "agent_id")
    val messageAgentId: Int? = 0,
    @SerialName(value = "body")
    val messageBody: String = "",
    @SerialName(value = "timestamp")
    val messageTimestamp: String = ""
)
