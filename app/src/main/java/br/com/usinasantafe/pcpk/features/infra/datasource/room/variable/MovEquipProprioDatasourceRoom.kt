package br.com.usinasantafe.pcpk.features.infra.datasource.room.variable

import br.com.usinasantafe.pcpk.features.infra.models.room.variable.MovEquipProprioRoomModel


interface MovEquipProprioDatasourceRoom {

    suspend fun listMovEquipProprioOpen(): List<MovEquipProprioRoomModel>

}