package br.com.usinasantafe.pcpk.features.module.datasource.room

import br.com.usinasantafe.pcpk.features.external.room.database.stable.ColabDatasourceRoomImpl
import br.com.usinasantafe.pcpk.features.external.room.database.stable.EquipDatasourceRoomImpl
import br.com.usinasantafe.pcpk.features.external.room.database.stable.LocalDatasourceRoomImpl
import br.com.usinasantafe.pcpk.features.external.room.database.stable.TerceiroDatasourceRoomImpl
import br.com.usinasantafe.pcpk.features.external.room.database.stable.VisitanteDatasourceRoomImpl
import br.com.usinasantafe.pcpk.features.infra.datasource.room.stable.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface StableRoomDatasourceModule {

    @Binds
    @Singleton
    fun bindColabDatasource(dataSource: ColabDatasourceRoomImpl): ColabDatasourceRoom

    @Binds
    @Singleton
    fun bindEquipDatasource(dataSource: EquipDatasourceRoomImpl): EquipDatasourceRoom

    @Binds
    @Singleton
    fun bindLocalDatasource(dataSource: LocalDatasourceRoomImpl): LocalDatasourceRoom

    @Binds
    @Singleton
    fun bindTerceiroDatasource(dataSource: TerceiroDatasourceRoomImpl): TerceiroDatasourceRoom

    @Binds
    @Singleton
    fun bindVisitanteDatasource(dataSource: VisitanteDatasourceRoomImpl): VisitanteDatasourceRoom

}