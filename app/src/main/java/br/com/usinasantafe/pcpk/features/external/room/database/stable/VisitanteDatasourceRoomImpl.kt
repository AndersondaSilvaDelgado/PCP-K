package br.com.usinasantafe.pcpk.features.external.room.database.stable

import br.com.usinasantafe.pcpk.features.external.room.dao.stable.VisitanteDao
import br.com.usinasantafe.pcpk.features.infra.datasource.room.stable.VisitanteDatasourceRoom
import br.com.usinasantafe.pcpk.features.infra.models.room.stable.VisitanteRoomModel
import javax.inject.Inject

class VisitanteDatasourceRoomImpl @Inject constructor (
    private val visitanteDao: VisitanteDao,
): VisitanteDatasourceRoom {

    override suspend fun addAllVisitante(vararg visitanteRoomModels: VisitanteRoomModel) {
        visitanteDao.insertAll(*visitanteRoomModels)
    }

    override suspend fun deleteAllVisitante() {
        visitanteDao.deleteAll()
    }

}