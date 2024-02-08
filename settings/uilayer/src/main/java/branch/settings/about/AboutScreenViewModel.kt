package branch.settings.about

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import branch.domainlayer.repository.AboutRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AboutScreenViewModel @Inject constructor(
    application: Application,
    private val aboutRepository: AboutRepository
) : AndroidViewModel(application = application) {

    fun getPrivacyPolicy(context: Context) {
        viewModelScope.launch {
            aboutRepository.getPrivacyPolicy(context = context)
        }
    }

    fun getTermsOfUse(context: Context) {
        viewModelScope.launch {
            aboutRepository.getTermsOfUse(context = context)
        }
    }

    fun getAppVersion(context: Context): String {
        return aboutRepository.getAppVersion(context = context)
    }

}