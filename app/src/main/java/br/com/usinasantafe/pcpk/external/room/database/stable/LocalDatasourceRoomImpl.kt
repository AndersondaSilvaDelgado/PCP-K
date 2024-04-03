package br.com.usinasantafe.pcpk.external.room.database.stable

import br.com.usinasantafe.pcpk.external.room.dao.stable.LocalDao
import br.com.usinasantafe.pcpk.infra.datasource.room.stable.LocalDatasourceRoom
import br.com.usinasantafe.pcpk.infra.models.room.stable.LocalRoomModel
import javax.inject.Inject

class LocalDatasourceRoomImpl @Inject constructor (
    private val localDao: LocalDao,
): LocalDatasourceRoom {

    override suspend fun addAllLocal(vararg localRoomModels: LocalRoomModel) {
        localDao.insertAll(*localRoomModels)
    }

    override suspend fun deleteAllLocal() {
        localDao.deleteAll()
    }

    override suspend fun listAllLocal(): List<LocalRoomModel> {
        return localDao.listAll()
    }

    override suspend fun getLocalId(id: Long): LocalRoomModel {
        return localDao.getLocalId(id)
    }

}