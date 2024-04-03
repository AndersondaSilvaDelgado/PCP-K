package br.com.usinasantafe.pcpk.infra.datasource.room.stable

import br.com.usinasantafe.pcpk.infra.models.room.stable.ColabRoomModel

interface ColabDatasourceRoom {

    suspend fun addAllColab(vararg colabRoomModels: ColabRoomModel)

    suspend fun checkColabMatric(matric: Long): Boolean

    suspend fun deleteAllColab()

    suspend fun getColabMatric(matric: Long): ColabRoomModel

}