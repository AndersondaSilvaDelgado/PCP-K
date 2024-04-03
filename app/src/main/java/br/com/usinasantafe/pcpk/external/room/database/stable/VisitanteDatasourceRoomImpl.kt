package br.com.usinasantafe.pcpk.external.room.database.stable

import br.com.usinasantafe.pcpk.external.room.dao.stable.VisitanteDao
import br.com.usinasantafe.pcpk.infra.datasource.room.stable.VisitanteDatasourceRoom
import br.com.usinasantafe.pcpk.infra.models.room.stable.VisitanteRoomModel
import javax.inject.Inject

class VisitanteDatasourceRoomImpl @Inject constructor (
    private val visitanteDao: VisitanteDao,
): VisitanteDatasourceRoom {

    override suspend fun addAllVisitante(vararg visitanteRoomModels: VisitanteRoomModel) {
        visitanteDao.insertAll(*visitanteRoomModels)
    }

    override suspend fun checkCPFVisitante(cpf: String): Boolean {
        return visitanteDao.checkCPFVisitante(cpf) > 0
    }

    override suspend fun deleteAllVisitante() {
        visitanteDao.deleteAll()
    }

    override suspend fun getVisitanteCPF(cpf: String): VisitanteRoomModel {
        return visitanteDao.getVisitanteCPF(cpf)
    }

    override suspend fun getVisitanteId(id: Long): VisitanteRoomModel {
        return visitanteDao.getVisitanteId(id)
    }

}