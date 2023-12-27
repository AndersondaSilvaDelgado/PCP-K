package br.com.usinasantafe.pcpk.features.external.room.dao.stable

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.usinasantafe.pcpk.common.utils.TB_LOCAL
import br.com.usinasantafe.pcpk.features.infra.models.room.stable.LocalRoomModel

@Dao
interface LocalDao {

    @Insert
    fun insertAll(vararg localRoomModels: LocalRoomModel)

    @Query("DELETE FROM $TB_LOCAL")
    suspend fun deleteAll()

    @Query("SELECT * FROM $TB_LOCAL ORDER BY descrLocal ASC")
    suspend fun listAll(): List<LocalRoomModel>

}