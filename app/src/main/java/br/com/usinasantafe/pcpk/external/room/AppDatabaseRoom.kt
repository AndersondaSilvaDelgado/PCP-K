package br.com.usinasantafe.pcpk.external.room

import androidx.room.*
import br.com.usinasantafe.pcpk.utils.VERSION_DB
import br.com.usinasantafe.pcpk.external.room.dao.stable.*
import br.com.usinasantafe.pcpk.external.room.dao.variable.*
import br.com.usinasantafe.pcpk.infra.models.room.stable.*
import br.com.usinasantafe.pcpk.infra.models.room.variable.*

@Database(
    entities = [
        ColabRoomModel::class,
        EquipRoomModel::class,
        LocalRoomModel::class,
        TerceiroRoomModel::class,
        VisitanteRoomModel::class,
        MovEquipProprioRoomModel::class,
        MovEquipProprioSegRoomModel::class,
        MovEquipProprioPassagRoomModel::class,
        MovEquipVisitTercRoomModel::class,
        MovEquipVisitTercPassagRoomModel::class,
        MovEquipResidenciaRoomModel::class,
               ], version = VERSION_DB, exportSchema = false
)
abstract class AppDatabaseRoom : RoomDatabase() {
    abstract fun colabDao(): ColabDao
    abstract fun equipDao(): EquipDao
    abstract fun localDao(): LocalDao
    abstract fun terceiroDao(): TerceiroDao
    abstract fun visitanteDao(): VisitanteDao
    abstract fun movEquipProprioDao(): MovEquipProprioDao
    abstract fun movEquipProprioSegDao(): MovEquipProprioSegDao
    abstract fun movEquipProprioPassagDao(): MovEquipProprioPassagDao
    abstract fun movEquipVisitTercDao(): MovEquipVisitTercDao
    abstract fun movEquipVisitTercPassagDao(): MovEquipVisitTercPassagDao
    abstract fun movEquipResidenciaDao(): MovEquipResidenciaDao
}


