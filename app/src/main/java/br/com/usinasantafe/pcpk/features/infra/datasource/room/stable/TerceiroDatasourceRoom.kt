package br.com.usinasantafe.pcpk.features.infra.datasource.room.stable

import br.com.usinasantafe.pcpk.features.infra.models.room.stable.TerceiroRoomModel

interface TerceiroDatasourceRoom {

    suspend fun addAllTerceiro(vararg terceiroRoomModels: TerceiroRoomModel)

    suspend fun checkCPFTerceiro(cpf: String): Boolean

    suspend fun deleteAllTerceiro()

    suspend fun getTerceiroCPF(cpf: String): TerceiroRoomModel

    suspend fun getTerceiroListCPF(cpf: String): List<TerceiroRoomModel>

    suspend fun getTerceiroId(id: Long): TerceiroRoomModel

}