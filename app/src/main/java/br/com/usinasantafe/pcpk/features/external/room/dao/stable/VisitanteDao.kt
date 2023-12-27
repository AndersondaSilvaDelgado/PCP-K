package br.com.usinasantafe.pcpk.features.external.room.dao.stable

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.usinasantafe.pcpk.common.utils.TB_VISITANTE
import br.com.usinasantafe.pcpk.features.infra.models.room.stable.VisitanteRoomModel

@Dao
interface VisitanteDao {

    @Insert
    fun insertAll(vararg visitanteRoomModels: VisitanteRoomModel)

    @Query("DELETE FROM $TB_VISITANTE")
    suspend fun deleteAll()

}