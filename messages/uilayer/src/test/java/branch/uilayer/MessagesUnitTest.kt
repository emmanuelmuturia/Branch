package branch.uilayer

import android.app.Application
import branch.domainlayer.BranchState
import branch.domainlayer.dto.BranchMessage
import branch.domainlayer.repository.BranchNetworkRepository
import branch.uilayer.conversation.ConversationScreenViewModel
import branch.uilayer.messages.MessagesScreenViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(value = RobolectricTestRunner::class)
@Config(sdk = [34], manifest = Config.NONE)
class MessagesUnitTest {

    @MockK
    private lateinit var mockKApplication: Application

    @MockK
    private lateinit var mockKBranchNetworkRepository: BranchNetworkRepository

    @InjectMockKs
    private lateinit var mockKMessagesScreenViewModel: MessagesScreenViewModel

    @InjectMockKs
    private lateinit var mockKConversationScreenViewModel: ConversationScreenViewModel

    private val messages = mutableListOf(
        BranchMessage(
            messageThreadId = 0,
            messageUserId = "0",
            messageAgentId = null,
            messageId = 0,
            messageBody = "This is Message #0",
            messageTimestamp = ""
        ),
        BranchMessage(
            messageThreadId = 1,
            messageUserId = "1",
            messageAgentId = null,
            messageId = 1,
            messageBody = "This is Message #1",
            messageTimestamp = ""
        ),
        BranchMessage(
            messageThreadId = 2,
            messageUserId = "2",
            messageAgentId = null,
            messageId = 2,
            messageBody = "This is Message #2",
            messageTimestamp = ""
        ),
        BranchMessage(
            messageThreadId = 3,
            messageUserId = "3",
            messageAgentId = null,
            messageId = 3,
            messageBody = "This is Message #3",
            messageTimestamp = ""
        ),
        BranchMessage(
            messageThreadId = 4,
            messageUserId = "4",
            messageAgentId = null,
            messageId = 4,
            messageBody = "This is Message #4",
            messageTimestamp = ""
        ),
        BranchMessage(
            messageThreadId = 5,
            messageUserId = "5",
            messageAgentId = null,
            messageId = 5,
            messageBody = "This is Message #5",
            messageTimestamp = ""
        ),
        BranchMessage(
            messageThreadId = 6,
            messageUserId = "6",
            messageAgentId = null,
            messageId = 6,
            messageBody = "This is Message #6",
            messageTimestamp = ""
        )
    )

    @Before
    fun setup() {

        MockKAnnotations.init(obj = arrayOf(this))

        coEvery { mockKBranchNetworkRepository.getMessages() } returns messages

        coEvery { mockKBranchNetworkRepository.createMessage(messageThreadId = 7, messageBody = "This is Message #7") } returns BranchMessage(
            messageThreadId = 7,
            messageBody = "This is Message #7"
        )

        coEvery { mockKBranchNetworkRepository.getMessagesByThread(threadId = 5) } coAnswers {
            messages.filter { it.messageThreadId == 5 }
        }

        coEvery { mockKBranchNetworkRepository.resetMessages() } returns Unit

        mockKMessagesScreenViewModel = MessagesScreenViewModel(
            application = mockKApplication,
            branchNetworkRepository = mockKBranchNetworkRepository
        )

        mockKConversationScreenViewModel = ConversationScreenViewModel(
            application = mockKApplication,
            branchNetworkRepository = mockKBranchNetworkRepository
        )

    }

    @Test
    fun `Test to confirm that getMessages() correctly updates the Branch State`() = runTest {
        mockKMessagesScreenViewModel.getMessages()
        val messagesState = mockKMessagesScreenViewModel.branchState.value
        assert(value = messagesState is BranchState.Success)
    }

    @Test
    fun `Test to confirm that createMessage() correctly adds a new BranchMessage`() = runBlocking {
        mockKConversationScreenViewModel.createMessage(messageThreadId = 7, messageBody = "This is Message #7")
        coVerify(exactly = 1) { mockKBranchNetworkRepository.createMessage(messageThreadId = 7, messageBody = "This is Message #7") }
    }

    @Test
    fun `Test to confirm that getMessagesByThreadId() returns a filtered list of BranchMessage`() = runBlocking {
        val result = mockKBranchNetworkRepository.getMessagesByThread(threadId = 5)
        assert(result.size == 1)
    }

    @Test
    fun `Test to confirm that resetMessages correctly resets the REST API customer messages`() {
        mockKMessagesScreenViewModel.reset()
        coVerify(exactly = 1) { mockKBranchNetworkRepository.resetMessages() }
    }

}