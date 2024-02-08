package branch.domainlayer.repository

import android.content.Context

interface AboutRepository {

    suspend fun getPrivacyPolicy(context: Context)

    suspend fun getTermsOfUse(context: Context)

    fun getAppVersion(context: Context): String

}