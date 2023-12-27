package br.com.usinasantafe.pcpk.features.external.room.dao.stable

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.usinasantafe.pcpk.common.utils.TB_TERCEIRO
import br.com.usinasantafe.pcpk.features.infra.models.room.stable.TerceiroRoomModel

@Dao
interface TerceiroDao {

    @Insert
    fun insertAll(vararg terceiroRoomModels: TerceiroRoomModel)

    @Query("DELETE FROM $TB_TERCEIRO")
    suspend fun deleteAll()

}