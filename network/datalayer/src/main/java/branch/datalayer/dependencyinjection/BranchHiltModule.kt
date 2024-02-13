package branch.datalayer.dependencyinjection

import branch.datalayer.interceptor.BranchInterceptor
import branch.datalayer.repository.BranchNetworkRepositoryImplementation
import branch.domainlayer.apiservice.BranchApiService
import branch.domainlayer.repository.BranchNetworkRepository
import branch.network.datalayer.BuildConfig
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BranchHiltModule {

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {

        val client = OkHttpClient.Builder().apply {
            addInterceptor(interceptor = BranchInterceptor())
        }.build()

        val json = Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }

        return Retrofit.Builder()
            .baseUrl(BuildConfig.baseUrl)
            .client(client)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()

    }

    @Provides
    @Singleton
    fun providesBranchApiService(retrofit: Retrofit): BranchApiService {
        return retrofit.create(BranchApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesBranchNetworkRepository(
        branchApiService: BranchApiService
    ): BranchNetworkRepository {
        return BranchNetworkRepositoryImplementation(
            branchApiService = branchApiService
        )
    }

}