package br.com.usinasantafe.pcpk.features.external.room.dao.variable

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.usinasantafe.pcpk.common.utils.TB_MOV_EQUIP_PROPRIO_SEG
import br.com.usinasantafe.pcpk.features.infra.models.room.variable.MovEquipProprioRoomModel
import br.com.usinasantafe.pcpk.features.infra.models.room.variable.MovEquipProprioSegRoomModel

@Dao
interface MovEquipProprioSegDao {

    @Insert
    suspend fun insertAll(vararg movEquipProprioSegRoomModels: MovEquipProprioSegRoomModel)

    @Insert
    suspend fun insert(movEquipProprioSegRoomModels: MovEquipProprioSegRoomModel): Long

    @Query("SELECT * FROM $TB_MOV_EQUIP_PROPRIO_SEG WHERE idMovEquipProprio = :idMov")
    suspend fun listMovEquipProprioSegIdMov(idMov: Long): List<MovEquipProprioSegRoomModel>
}