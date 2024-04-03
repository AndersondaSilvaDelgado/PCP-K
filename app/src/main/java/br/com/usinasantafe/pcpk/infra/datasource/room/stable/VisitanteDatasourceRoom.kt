package br.com.usinasantafe.pcpk.infra.datasource.room.stable

import br.com.usinasantafe.pcpk.infra.models.room.stable.VisitanteRoomModel

interface VisitanteDatasourceRoom {

    suspend fun addAllVisitante(vararg visitanteRoomModels: VisitanteRoomModel)

    suspend fun checkCPFVisitante(cpf: String): Boolean

    suspend fun deleteAllVisitante()

    suspend fun getVisitanteCPF(cpf: String): VisitanteRoomModel

    suspend fun getVisitanteId(id: Long): VisitanteRoomModel

}