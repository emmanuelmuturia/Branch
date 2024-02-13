package branch.uilayer.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import branch.domainlayer.BranchState
import branch.domainlayer.repository.BranchNetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    application: Application,
    private val branchNetworkRepository: BranchNetworkRepository
) : AndroidViewModel(application = application) {

    private var _isLoginSuccessful: MutableStateFlow<Boolean> = MutableStateFlow(value = false)
    val isLoginSuccessful: StateFlow<Boolean> = _isLoginSuccessful.asStateFlow()

    fun login(username: String, password: String) {

        viewModelScope.launch {

            try {
                branchNetworkRepository.login(
                    username = username,
                    password = password
                )
                _isLoginSuccessful.value = true
            } catch (e: Exception) {
                _isLoginSuccessful.value = false
            }

        }

    }

}