package branch.uilayer.messages

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import branch.domainlayer.BranchState
import branch.domainlayer.dto.BranchMessage
import branch.domainlayer.repository.BranchNetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MessagesScreenViewModel @Inject constructor(
    application: Application,
    private val branchNetworkRepository: BranchNetworkRepository
) : AndroidViewModel(application = application) {

    private var _branchState: MutableStateFlow<BranchState<Any>> = MutableStateFlow(value = BranchState.Loading)
    val branchState: StateFlow<BranchState<Any>> = _branchState.asStateFlow()

    private var _branchMessages: MutableStateFlow<List<BranchMessage>> = MutableStateFlow(value = emptyList())
    val branchMessages: StateFlow<List<BranchMessage>> = _branchMessages.asStateFlow()

    init {
        getMessages()
    }

    fun getMessages() {

        viewModelScope.launch {

            try {
                _branchState.update { BranchState.Loading }
                _branchMessages.value = branchNetworkRepository.getMessages()
                _branchState.update { BranchState.Success(data = _branchMessages.value) }
            } catch (e: Exception) {
                _branchState.update { BranchState.Error(error = e) }
            }

        }

    }

    fun reset() {
        viewModelScope.launch {
            branchNetworkRepository.reset()
        }
    }

}