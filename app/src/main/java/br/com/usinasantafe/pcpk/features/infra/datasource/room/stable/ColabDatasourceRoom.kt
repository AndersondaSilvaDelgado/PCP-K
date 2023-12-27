package br.com.usinasantafe.pcpk.features.infra.datasource.room.stable

import br.com.usinasantafe.pcpk.features.infra.models.room.stable.ColabRoomModel

interface ColabDatasourceRoom {

    suspend fun addAllColab(vararg colabRoomModels: ColabRoomModel)

    suspend fun deleteAllColab()

    suspend fun checkColabMatric(matric: Long): Boolean

    suspend fun getColabMatric(matric: Long): ColabRoomModel

}