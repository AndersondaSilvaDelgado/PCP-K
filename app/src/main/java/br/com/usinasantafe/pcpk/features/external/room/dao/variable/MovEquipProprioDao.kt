package br.com.usinasantafe.pcpk.features.external.room.dao.variable

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.usinasantafe.pcpk.common.utils.StatusData
import br.com.usinasantafe.pcpk.common.utils.StatusSend
import br.com.usinasantafe.pcpk.common.utils.TB_MOV_EQUIP_PROPRIO
import br.com.usinasantafe.pcpk.features.infra.models.room.variable.MovEquipProprioRoomModel

@Dao
interface MovEquipProprioDao {

    @Insert
    suspend fun insert(movEquipProprioRoomModel: MovEquipProprioRoomModel)

    @Update
    suspend fun update(movEquipProprioRoomModel: MovEquipProprioRoomModel)

    @Query("SELECT * FROM $TB_MOV_EQUIP_PROPRIO WHERE statusMovEquipProprio = :status")
    suspend fun listMovStatus(status: StatusData): List<MovEquipProprioRoomModel>

    @Query("SELECT * FROM $TB_MOV_EQUIP_PROPRIO WHERE statusSendMovEquipProprio = :statusEnvio")
    suspend fun listMovStatusEnvio(statusEnvio: StatusSend): List<MovEquipProprioRoomModel>

    @Query("SELECT MAX(idMovEquipProprio) FROM $TB_MOV_EQUIP_PROPRIO WHERE statusSendMovEquipProprio = :statusEnvio")
    suspend fun lastIdMovStatusEnvio(statusEnvio: StatusSend): Long

    @Query("SELECT * FROM $TB_MOV_EQUIP_PROPRIO WHERE idMovEquipProprio = :idMov")
    suspend fun listMovId(idMov: Long): List<MovEquipProprioRoomModel>

}