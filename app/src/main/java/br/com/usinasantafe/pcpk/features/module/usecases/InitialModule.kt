package br.com.usinasantafe.pcpk.features.module.usecases

import br.com.usinasantafe.pcpk.features.domain.usecases.implementations.initial.CheckPasswordConfigImpl
import br.com.usinasantafe.pcpk.features.domain.usecases.implementations.initial.RecoverConfigImpl
import br.com.usinasantafe.pcpk.features.domain.usecases.implementations.initial.SaveConfigImpl
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.initial.CheckPasswordConfig
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.initial.RecoverConfig
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.initial.SaveConfig
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface InitialModule {

    @Singleton
    @Binds
    fun bindRecoverConfig(usecase: RecoverConfigImpl): RecoverConfig

    @Singleton
    @Binds
    fun bindCheckPasswordConfig(usecase: CheckPasswordConfigImpl): CheckPasswordConfig

    @Singleton
    @Binds
    fun bindSaveConfig(usecase: SaveConfigImpl): SaveConfig

}