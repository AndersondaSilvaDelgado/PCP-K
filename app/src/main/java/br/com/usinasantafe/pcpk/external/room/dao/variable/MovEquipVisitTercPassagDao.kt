package br.com.usinasantafe.pcpk.external.room.dao.variable

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import br.com.usinasantafe.pcpk.utils.TB_MOV_EQUIP_VISIT_TERC_PASSAG
import br.com.usinasantafe.pcpk.infra.models.room.variable.MovEquipProprioPassagRoomModel
import br.com.usinasantafe.pcpk.infra.models.room.variable.MovEquipVisitTercPassagRoomModel

@Dao
interface MovEquipVisitTercPassagDao {

    @Insert
    suspend fun insertAll(vararg movEquipVisitTercPassagRoomModels: MovEquipVisitTercPassagRoomModel)

    @Insert
    suspend fun insert(movEquipVisitTercPassagRoomModel: MovEquipVisitTercPassagRoomModel)

    @Delete
    suspend fun delete(movEquipVisitTercPassagRoomModel: MovEquipVisitTercPassagRoomModel)

    @Query("SELECT * FROM $TB_MOV_EQUIP_VISIT_TERC_PASSAG WHERE idMovEquipVisitTerc = :idMov")
    suspend fun listMovEquipVisitTercPassagIdMov(idMov: Long): List<MovEquipVisitTercPassagRoomModel>

}