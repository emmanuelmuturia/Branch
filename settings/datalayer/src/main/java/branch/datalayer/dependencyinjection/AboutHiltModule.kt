package branch.datalayer.dependencyinjection

import branch.datalayer.repository.AboutRepositoryImplementation
import branch.domainlayer.repository.AboutRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AboutHiltModule {

    @Provides
    fun providesAboutRepository(): AboutRepository {
        return AboutRepositoryImplementation()
    }

}