package br.com.usinasantafe.pcpk.features.module.repository

import br.com.usinasantafe.pcpk.features.domain.repositories.variable.ConfigRepository
import br.com.usinasantafe.pcpk.features.infra.repositories.variable.ConfigRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface VariableRepositoryModule {

    @Singleton
    @Binds
    fun bindConfigRepository(repository: ConfigRepositoryImpl): ConfigRepository

}