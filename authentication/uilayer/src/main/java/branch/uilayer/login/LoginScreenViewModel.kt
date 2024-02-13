package branch.uilayer.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import branch.domainlayer.BranchState
import branch.domainlayer.dto.LoginResponse
import branch.domainlayer.repository.BranchNetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    application: Application,
    private val branchNetworkRepository: BranchNetworkRepository
) : AndroidViewModel(application = application) {

    private var isLoginSuccessful: Boolean = false

    private var _loginState: MutableStateFlow<BranchState<Any>> = MutableStateFlow(value = BranchState.Loading)
    val loginState: StateFlow<BranchState<Any>> = _loginState.asStateFlow()

    suspend fun login(username: String, password: String) {

        return try {
            _loginState.update { BranchState.Success(data = branchNetworkRepository.login(username = username, password = password)) }
            isLoginSuccessful = true
        } catch (e: Exception) {
            isLoginSuccessful = false
            _loginState.update { BranchState.Error(error = e) }
        }

    }

}