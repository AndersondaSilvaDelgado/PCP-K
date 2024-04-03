package br.com.usinasantafe.pcpk.infra.datasource.room.stable

import br.com.usinasantafe.pcpk.infra.models.room.stable.LocalRoomModel

interface LocalDatasourceRoom {

    suspend fun addAllLocal(vararg localRoomModels: LocalRoomModel)

    suspend fun deleteAllLocal()

    suspend fun listAllLocal(): List<LocalRoomModel>

    suspend fun getLocalId(id: Long): LocalRoomModel

}