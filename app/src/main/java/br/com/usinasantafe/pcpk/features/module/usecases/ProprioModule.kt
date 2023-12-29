package br.com.usinasantafe.pcpk.features.module.usecases

import br.com.usinasantafe.pcpk.features.domain.usecases.implementations.proprio.*
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ProprioModule {

    @Binds
    @Singleton
    fun bindRecoverListMovEquipProprio(usecase: RecoverListMovEquipProprioImpl): RecoverListMovEquipProprio

    @Binds
    @Singleton
    fun bindRecoverNomeColabMotoristaPassag(usecase: RecoverNomeColabMotoristaPassagImpl): RecoverNomeColabMotoristaPassag

    @Binds
    @Singleton
    fun bindSetMatricMotoristaPassag(usecase: SetMatricMotoristaPassagImpl): SetMatricMotoristaPassag

    @Binds
    @Singleton
    fun bindStartMovEquipProprio(usecase: StartMovEquipProprioImpl): StartMovEquipProprio

}