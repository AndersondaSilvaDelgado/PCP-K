package br.com.usinasantafe.pcpk.features.external.room.dao.variable

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.usinasantafe.pcpk.common.utils.StatusData
import br.com.usinasantafe.pcpk.common.utils.StatusSend
import br.com.usinasantafe.pcpk.common.utils.TB_MOV_EQUIP_VISIT_TERC
import br.com.usinasantafe.pcpk.features.infra.models.room.variable.MovEquipVisitTercRoomModel


@Dao
interface MovEquipVisitTercDao {

    @Insert
    suspend fun insert(movEquipVisitTercRoomModel: MovEquipVisitTercRoomModel)

    @Update
    suspend fun update(movEquipVisitTercRoomModel: MovEquipVisitTercRoomModel)

    @Delete
    suspend fun delete(movEquipVisitTercRoomModel: MovEquipVisitTercRoomModel)

    @Query("SELECT * FROM $TB_MOV_EQUIP_VISIT_TERC WHERE statusMovEquipVisitTerc = :status")
    suspend fun listMovStatus(status: StatusData): List<MovEquipVisitTercRoomModel>

    @Query("SELECT * FROM $TB_MOV_EQUIP_VISIT_TERC WHERE statusSendMovEquipVisitTerc = :statusEnvio")
    suspend fun listMovStatusEnvio(statusEnvio: StatusSend): List<MovEquipVisitTercRoomModel>

    @Query("SELECT MAX(idMovEquipVisitTerc) FROM $TB_MOV_EQUIP_VISIT_TERC WHERE statusSendMovEquipVisitTerc = :statusEnvio")
    suspend fun lastIdMovStatusEnvio(statusEnvio: StatusSend): Long

    @Query("SELECT * FROM $TB_MOV_EQUIP_VISIT_TERC WHERE idMovEquipVisitTerc = :idMov")
    suspend fun listMovId(idMov: Long): List<MovEquipVisitTercRoomModel>

}