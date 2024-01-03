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

    override suspend fun checkEquipNro(nro: Long): Boolean {
        return equipDao.checkEquipNro(nro) > 0
    }

    override suspend fun getEquipNro(nro: Long): EquipRoomModel {
        return equipDao.getEquipNro(nro)
    }

    override suspend fun getEquipId(id: Long): EquipRoomModel {
        return equipDao.getEquipId(id)
    }

}