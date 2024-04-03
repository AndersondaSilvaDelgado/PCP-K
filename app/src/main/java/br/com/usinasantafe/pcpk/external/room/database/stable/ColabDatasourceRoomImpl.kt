package br.com.usinasantafe.pcpk.external.room.database.stable

import br.com.usinasantafe.pcpk.external.room.dao.stable.ColabDao
import br.com.usinasantafe.pcpk.infra.datasource.room.stable.ColabDatasourceRoom
import br.com.usinasantafe.pcpk.infra.models.room.stable.ColabRoomModel
import javax.inject.Inject

class ColabDatasourceRoomImpl @Inject constructor (
    private val colabDao: ColabDao,
): ColabDatasourceRoom {

    override suspend fun addAllColab(vararg colabRoomModels: ColabRoomModel) {
        colabDao.insertAll(*colabRoomModels)
    }

    override suspend fun checkColabMatric(matric: Long): Boolean {
        return colabDao.checkColabMatric(matric) > 0
    }

    override suspend fun deleteAllColab() {
        colabDao.deleteAll()
    }

    override suspend fun getColabMatric(matric: Long): ColabRoomModel {
        return colabDao.getColabMatric(matric)
    }

}