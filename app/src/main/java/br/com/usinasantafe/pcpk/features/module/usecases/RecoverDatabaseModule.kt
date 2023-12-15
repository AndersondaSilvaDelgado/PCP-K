package br.com.usinasantafe.pcpk.features.module.usecases

import br.com.usinasantafe.pcpk.features.domain.usecases.implementations.database.recover.RecoverTokenImpl
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.database.recover.RecoverToken
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RecoverDatabaseModule {

    @Singleton
    @Binds
    fun bindRecoverToken(usecase: RecoverTokenImpl): RecoverToken

}