package br.com.usinasantafe.pcpk.features.external.room.dao.variable

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.usinasantafe.pcpk.common.utils.TB_MOV_EQUIP_VISIT_TERC_PASSAG
import br.com.usinasantafe.pcpk.features.infra.models.room.variable.MovEquipVisitTercPassagRoomModel

@Dao
interface MovEquipVisitTercPassagDao {

    @Insert
    suspend fun insertAll(vararg movEquipVisitTercPassagRoomModels: MovEquipVisitTercPassagRoomModel)

    @Query("SELECT * FROM $TB_MOV_EQUIP_VISIT_TERC_PASSAG WHERE idMovEquipVisitTerc = :idMov")
    suspend fun listMovEquipVisitTercPassagIdMov(idMov: Long): List<MovEquipVisitTercPassagRoomModel>

}