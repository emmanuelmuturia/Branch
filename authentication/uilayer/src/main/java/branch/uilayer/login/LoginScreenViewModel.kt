package branch.uilayer.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import branch.domainlayer.repository.BranchNetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    application: Application,
    private val branchNetworkRepository: BranchNetworkRepository
) : AndroidViewModel(application = application) {

    fun login(username: String, password: String) {
        viewModelScope.launch {
            branchNetworkRepository.login(
                username = username,
                password = password
            )
        }
    }

}