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

    private var _branchState: MutableStateFlow<BranchState<Any>> = MutableStateFlow(value = BranchState.Loading)
    val branchState: StateFlow<BranchState<Any>> = _branchState.asStateFlow()

    fun getMessagesByThread(threadId: Int) {

        viewModelScope.launch {

            _branchState.update { BranchState.Loading }

            try {
                _branchMessages.value = branchNetworkRepository.getMessagesByThread(threadId = threadId)
                _branchState.update { BranchState.Success(data = _branchMessages.value) }
            } catch (e: Exception) {
                _branchState.update { BranchState.Error(error = e) }
            }

        }

    }

    suspend fun createMessage(messageThreadId: Int, messageBody: String): BranchMessage {
        return branchNetworkRepository.createMessage(
            messageThreadId = messageThreadId,
            messageBody = messageBody
        )
    }

}