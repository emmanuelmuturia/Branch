package branch.domainlayer.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MessageRequest(
    @SerialName("thread_id")
    val messageThreadId: Int,
    @SerialName("body")
    val messageBody: String
)
