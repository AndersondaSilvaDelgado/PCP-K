package br.com.usinasantafe.pcpk.features.external.room.database.variable

import br.com.usinasantafe.pcpk.features.external.room.dao.variable.MovEquipProprioSegDao
import br.com.usinasantafe.pcpk.features.infra.datasource.room.variable.MovEquipProprioSegDatasourceRoom
import br.com.usinasantafe.pcpk.features.infra.models.room.variable.MovEquipProprioSegRoomModel
import javax.inject.Inject

class MovEquipProprioSegDatasourceRoomImpl @Inject constructor (
    private val movEquipProprioSegDao: MovEquipProprioSegDao,
): MovEquipProprioSegDatasourceRoom {

    override suspend fun addAllMovEquipProprioSeg(vararg movEquipProprioSegRoomModels: MovEquipProprioSegRoomModel): Boolean {
        try {
            movEquipProprioSegDao.insertAll(*movEquipProprioSegRoomModels)
        } catch (exception: Exception){
            return false
        }
        return true
    }

    override suspend fun addMovEquipProprioSeg(
        movEquipProprioSegRoomModel: MovEquipProprioSegRoomModel
    ): Boolean {
        return movEquipProprioSegDao.insert(movEquipProprioSegRoomModel) > 0
    }

    override suspend fun listMovEquipProprioSegIdMov(idMov: Long): List<MovEquipProprioSegRoomModel> {
        return movEquipProprioSegDao.listMovEquipProprioSegIdMov(idMov)
    }

    override suspend fun deleteMovEquipProprioSeg(movEquipProprioSegRoomModel: MovEquipProprioSegRoomModel): Boolean {
        return movEquipProprioSegDao.delete(movEquipProprioSegRoomModel) > 0
    }

}