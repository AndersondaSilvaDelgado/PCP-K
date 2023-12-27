package br.com.usinasantafe.pcpk.features.external.room

import androidx.room.*
import br.com.usinasantafe.pcpk.common.utils.VERSION_DB
import br.com.usinasantafe.pcpk.features.external.room.dao.stable.*
import br.com.usinasantafe.pcpk.features.infra.models.room.stable.*

@Database(
    entities = [
        ColabRoomModel::class,
        EquipRoomModel::class,
        LocalRoomModel::class,
        TerceiroRoomModel::class,
        VisitanteRoomModel::class,
               ], version = VERSION_DB, exportSchema = false
)
abstract class AppDatabaseRoom : RoomDatabase() {
    abstract fun colabDao(): ColabDao
    abstract fun equipDao(): EquipDao
    abstract fun localDao(): LocalDao
    abstract fun terceiroDao(): TerceiroDao
    abstract fun visitanteDao(): VisitanteDao
}


