package br.com.usinasantafe.pcpk.infra.datasource.room.variable

import br.com.usinasantafe.pcpk.infra.models.room.variable.MovEquipVisitTercPassagRoomModel

interface MovEquipVisitTercPassagDatasourceRoom {

    suspend fun addAllMovEquipVisitTercPassag(vararg movEquipVisitTercPassagRoomModels: MovEquipVisitTercPassagRoomModel): Boolean

    suspend fun addMovEquipVisitTercPassag(movEquipVisitTercPassagRoomModel: MovEquipVisitTercPassagRoomModel): Boolean

    suspend fun deleteMovEquipVisitTercPassag(movEquipVisitTercPassagRoomModel: MovEquipVisitTercPassagRoomModel): Boolean

    suspend fun listMovEquipVisitTercPassagIdMov(idMov: Long): List<MovEquipVisitTercPassagRoomModel>

}