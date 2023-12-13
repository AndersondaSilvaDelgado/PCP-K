package br.com.usinasantafe.pcpk.features.module.usecases

import br.com.usinasantafe.pcpk.features.domain.usecases.implementations.StartAppImpl
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.StartApp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface CommonModule {

    @Singleton
    @Binds
    fun bindStartAPP(usecase: StartAppImpl): StartApp

}