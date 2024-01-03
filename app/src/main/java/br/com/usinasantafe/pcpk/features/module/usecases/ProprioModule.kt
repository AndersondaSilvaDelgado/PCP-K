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
    fun bindClearEquipProprioSeg(usecase: ClearEquipProprioSegImpl): ClearEquipProprioSeg

    @Binds
    @Singleton
    fun bindDeleteColabPassag(usecase: DeleteColabPassagImpl): DeleteColabPassag

    @Binds
    @Singleton
    fun bindDeleteEquipProprioSeg(usecase: DeleteEquipProprioSegImpl): DeleteEquipProprioSeg

    @Binds
    @Singleton
    fun bindGetStatusMov(usecase: GetStatusMovImpl): GetStatusMov

    @Binds
    @Singleton
    fun bindGetTipoMov(usecase: GetTipoMovImpl): GetTipoMov

    @Binds
    @Singleton
    fun bindRecoverListEquipProprioSeg(usecase: RecoverListEquipProprioSegImpl): RecoverListEquipProprioSeg

    @Binds
    @Singleton
    fun bindRecoverListMovEquipProprio(usecase: RecoverListMovEquipProprioOpenImpl): RecoverListMovEquipProprioOpen

    @Binds
    @Singleton
    fun bindRecoverListPassag(usecase: RecoverListColabPassagImpl): RecoverListColabPassag

    @Binds
    @Singleton
    fun bindRecoverNomeColabMotoristaPassag(usecase: RecoverNomeColabImpl): RecoverNomeColab

    @Binds
    @Singleton
    fun bindSetDestinoProprio(usecase: SetDestinoProprioImpl): SetDestinoProprio

    @Binds
    @Singleton
    fun bindSetMatricMotoristaPassag(usecase: SetMatricMotoristaPassagImpl): SetMatricMotoristaPassag

    @Binds
    @Singleton
    fun bindSetNotaFiscalProprio(usecase: SetNotaFiscalProprioImpl): SetNotaFiscalProprio

    @Binds
    @Singleton
    fun bindSetNroVeiculoEquipSeg(usecase: SetNroVeiculoEquipSegImpl): SetNroVeiculoEquipSeg

    @Binds
    @Singleton
    fun bindSetStatusMov(usecase: SetStatusMovImpl): SetStatusMov

    @Binds
    @Singleton
    fun bindStartMovEquipProprio(usecase: StartMovEquipProprioImpl): StartMovEquipProprio

}