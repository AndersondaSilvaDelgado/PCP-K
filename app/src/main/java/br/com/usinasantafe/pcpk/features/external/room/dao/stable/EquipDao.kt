package br.com.usinasantafe.pcpk.features.external.room.dao.stable

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.usinasantafe.pcpk.common.utils.TB_EQUIP
import br.com.usinasantafe.pcpk.features.infra.models.room.stable.EquipRoomModel

@Dao
interface EquipDao {

    @Insert
    fun insertAll(vararg equipRoomModels: EquipRoomModel)

    @Query("DELETE FROM $TB_EQUIP")
    suspend fun deleteAll()

}