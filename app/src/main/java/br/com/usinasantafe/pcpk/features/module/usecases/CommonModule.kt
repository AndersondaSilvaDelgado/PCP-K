package br.com.usinasantafe.pcpk.features.module.usecases

import br.com.usinasantafe.pcpk.features.domain.usecases.implementations.common.CheckUpdateImpl
import br.com.usinasantafe.pcpk.features.domain.usecases.implementations.common.StartAppImpl
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.common.CheckUpdate
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.common.StartApp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
interface CommonModule {

    @Singleton
    @Binds
    fun bindCheckUpdate(usecase: CheckUpdateImpl): CheckUpdate

    @Singleton
    @Binds
    fun bindStartAPP(usecase: StartAppImpl): StartApp

}