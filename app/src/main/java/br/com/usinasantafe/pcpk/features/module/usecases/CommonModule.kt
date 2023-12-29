package br.com.usinasantafe.pcpk.features.module.usecases

import br.com.usinasantafe.pcpk.features.domain.usecases.implementations.common.*
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.common.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
interface CommonModule {

    @Binds
    @Singleton
    fun bindCheckMatricColab(usecase: CheckMatricColabImpl): CheckMatricColab

    @Binds
    @Singleton
    fun bindRecoverBase(usecase: RecoverBaseImpl): RecoverBase

    @Binds
    @Singleton
    fun bindStartAPP(usecase: StartAppImpl): StartApp

}