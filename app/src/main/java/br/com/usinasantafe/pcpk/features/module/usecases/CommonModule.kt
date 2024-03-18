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
    fun bindCheckMovOpen(usecase: CheckMovOpenImpl): CheckMovOpen

    @Binds
    @Singleton
    fun bindCheckStatusSend(usecase: CheckStatusSendImpl): CheckStatusSend

    @Singleton
    @Binds
    fun bindCloseSendAllMov(usecase: CloseSendAllMovImpl): CloseSendAllMov

    @Binds
    @Singleton
    fun bindDeleteMovSent(usecase: DeleteMovSentImpl): DeleteMovSent

    @Binds
    @Singleton
    fun bindGetStatusSendConfig(usecase: GetStatusSendConfigImpl): GetStatusSendConfig

    @Binds
    @Singleton
    fun bindRecoverBase(usecase: RecoverHeaderImpl): RecoverHeader

    @Binds
    @Singleton
    fun bindSetStatusSendConfig(usecase: SetStatusSendConfigImpl): SetStatusSendConfig

    @Binds
    @Singleton
    fun bindStartAPP(usecase: StartAppImpl): StartApp

    @Binds
    @Singleton
    fun bindStartProcessSendData(usecase: StartProcessSendDataImpl): StartProcessSendData

}