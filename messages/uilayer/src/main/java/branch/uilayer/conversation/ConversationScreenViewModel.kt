package branch.uilayer.conversation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import branch.domainlayer.BranchState
import branch.domainlayer.dto.BranchMessage
import branch.domainlayer.repository.BranchNetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConversationScreenViewModel @Inject constructor(
    application: Application,
    private val branchNetworkRepository: BranchNetworkRepository
) : AndroidViewModel(application = application) {

    private var _branchMessages: MutableStateFlow<List<BranchMessage>> =
        MutableStateFlow(value = emptyList())
    val branchMessages: StateFlow<List<BranchMessage>> = _branchMessages.asStateFlow()

    private var _isLoading = MutableStateFlow(value = false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun getMessagesByThread(threadId: Int) {

        viewModelScope.launch {

            _branchMessages.value = branchNetworkRepository.getMessagesByThread(threadId = threadId)

        }

    }


    fun refreshGetMessagesByThread(threadId: Int) {

        viewModelScope.launch {

            _isLoading.value = true
            branchNetworkRepository.getMessagesByThread(threadId = threadId)
            delay(timeMillis = 1400L)
            _isLoading.value = false

        }

    }

    suspend fun createMessage(messageThreadId: Int, messageBody: String): BranchMessage {
        return branchNetworkRepository.createMessage(
            messageThreadId = messageThreadId,
            messageBody = messageBody
        )
    }

}