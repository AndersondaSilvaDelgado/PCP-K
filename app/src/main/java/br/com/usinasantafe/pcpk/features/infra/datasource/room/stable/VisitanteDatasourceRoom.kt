package br.com.usinasantafe.pcpk.features.infra.datasource.room.stable

import br.com.usinasantafe.pcpk.features.infra.models.room.stable.VisitanteRoomModel

interface VisitanteDatasourceRoom {

    suspend fun addAllVisitante(vararg visitanteRoomModels: VisitanteRoomModel)

    suspend fun deleteAllVisitante()

}