package br.com.usinasantafe.pcpk.features.module.usecases

import br.com.usinasantafe.pcpk.features.domain.usecases.implementations.residencia.*
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.residencia.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ResidenciaModule {

    @Binds
    @Singleton
    fun bindCheckDataSendMovEquipResidencia (usecase: CheckDataSendMovEquipResidenciaImpl): CheckDataSendMovEquipResidencia

    @Binds
    @Singleton
    fun bindReceiverSentDataMovEquipResidencia (usecase: ReceiverSentDataMovEquipResidenciaImpl): ReceiverSentDataMovEquipResidencia

    @Binds
    @Singleton
    fun bindRecoverDetalheMovEquipResidencia (usecase: RecoverDetalheMovEquipResidenciaImpl): RecoverDetalheMovEquipResidencia

    @Binds
    @Singleton
    fun bindRecoverListMovEquipResidenciaOpen (usecase: RecoverListMovEquipResidenciaOpenImpl): RecoverListMovEquipResidenciaOpen

    @Binds
    @Singleton
    fun bindRecoverListMovEquipResidenciaStarted (usecase: RecoverListMovEquipResidenciaStartedImpl): RecoverListMovEquipResidenciaStarted

    @Binds
    @Singleton
    fun bindSaveMovEquipResidencia (usecase: SaveMovEquipResidenciaImpl): SaveMovEquipResidencia

    @Binds
    @Singleton
    fun bindSendDataMovEquipResidencia (usecase: SendDataMovEquipResidenciaImpl): SendDataMovEquipResidencia

    @Binds
    @Singleton
    fun bindSetMotoristaResidencia (usecase: SetMotoristaResidenciaImpl): SetMotoristaResidencia

    @Binds
    @Singleton
    fun bindSetObservResidencia (usecase: SetObservResidenciaImpl): SetObservResidencia

    @Binds
    @Singleton
    fun bindSetPlacaResidencia (usecase: SetPlacaResidenciaImpl): SetPlacaResidencia

    @Binds
    @Singleton
    fun bindSetStatusSendCloseAllMovResidencia (usecase: SetStatusSendCloseAllMovResidenciaImpl): SetStatusSendCloseAllMovResidencia

    @Binds
    @Singleton
    fun bindSetStatusSendCloseMovResidencia (usecase: SetStatusSendCloseMovResidenciaImpl): SetStatusSendCloseMovResidencia

    @Binds
    @Singleton
    fun bindSetVeiculoResidencia (usecase: SetVeiculoResidenciaImpl): SetVeiculoResidencia

    @Binds
    @Singleton
    fun bindStartMovEquipResidencia (usecase: StartMovEquipResidenciaImpl): StartMovEquipResidencia

}