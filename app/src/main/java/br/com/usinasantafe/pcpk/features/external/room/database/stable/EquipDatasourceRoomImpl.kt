package br.com.usinasantafe.pcpk.features.external.room.database.stable

import br.com.usinasantafe.pcpk.features.external.room.dao.stable.EquipDao
import br.com.usinasantafe.pcpk.features.infra.datasource.room.stable.EquipDatasourceRoom
import br.com.usinasantafe.pcpk.features.infra.models.room.stable.EquipRoomModel
import javax.inject.Inject

class EquipDatasourceRoomImpl @Inject constructor (
    private val equipDao: EquipDao,
): EquipDatasourceRoom {

    override suspend fun addAllEquip(vararg equipRoomModels: EquipRoomModel) {
        equipDao.insertAll(*equipRoomModels)
    }

    override suspend fun deleteAllEquip() {
        equipDao.deleteAll()
    }

}