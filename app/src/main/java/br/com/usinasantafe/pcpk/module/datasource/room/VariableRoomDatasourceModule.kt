package br.com.usinasantafe.pcpk.module.datasource.room

import br.com.usinasantafe.pcpk.external.room.database.variable.*
import br.com.usinasantafe.pcpk.infra.datasource.room.variable.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface VariableRoomDatasourceModule {

    @Binds
    @Singleton
    fun bindMovEquipProprioPassagDatasourceRoom(dataSource: MovEquipProprioPassagDatasourceRoomImpl): MovEquipProprioPassagDatasourceRoom

    @Binds
    @Singleton
    fun bindMovEquipProprioDatasourceRoom(dataSource: MovEquipProprioDatasourceRoomImpl): MovEquipProprioDatasourceRoom

    @Binds
    @Singleton
    fun bindMovEquipProprioSegDatasourceRoom(dataSource: MovEquipProprioSegDatasourceRoomImpl): MovEquipProprioSegDatasourceRoom

    @Binds
    @Singleton
    fun bindMovEquipResidenciaDatasourceRoom(dataSource: MovEquipResidenciaDatasourceRoomImpl): MovEquipResidenciaDatasourceRoom

    @Binds
    @Singleton
    fun bindMovEquipVisitTercDatasourceRoom(dataSource: MovEquipVisitTercDatasourceRoomImpl): MovEquipVisitTercDatasourceRoom

    @Binds
    @Singleton
    fun bindMovEquipVisitTercPassagDatasourceRoom(dataSource: MovEquipVisitTercPassagDatasourceRoomImpl): MovEquipVisitTercPassagDatasourceRoom

}