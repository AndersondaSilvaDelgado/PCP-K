package br.com.usinasantafe.pcpk.features.external.room.dao.variable

import androidx.room.Dao
import androidx.room.Query
import br.com.usinasantafe.pcpk.common.utils.StatusData
import br.com.usinasantafe.pcpk.common.utils.TB_MOV_EQUIP_PROPRIO
import br.com.usinasantafe.pcpk.features.infra.models.room.variable.MovEquipProprioRoomModel

@Dao
interface MovEquipProprioDao {

    @Query("SELECT * FROM $TB_MOV_EQUIP_PROPRIO WHERE statusMovEquipProprio = :status")
    suspend fun listBoletimStatus(status: StatusData): List<MovEquipProprioRoomModel>

}