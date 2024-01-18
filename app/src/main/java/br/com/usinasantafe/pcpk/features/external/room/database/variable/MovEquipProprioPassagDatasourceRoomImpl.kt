package br.com.usinasantafe.pcpk.features.external.room.database.variable

import br.com.usinasantafe.pcpk.features.external.room.dao.variable.MovEquipProprioPassagDao
import br.com.usinasantafe.pcpk.features.infra.datasource.room.variable.MovEquipProprioPassagDatasourceRoom
import br.com.usinasantafe.pcpk.features.infra.models.room.variable.MovEquipProprioPassagRoomModel
import javax.inject.Inject

class MovEquipProprioPassagDatasourceRoomImpl @Inject constructor (
    private val movEquipProprioPassagDao: MovEquipProprioPassagDao,
): MovEquipProprioPassagDatasourceRoom {

    override suspend fun addAllMovEquipProprioPassag(vararg movEquipProprioPassagRoomModels: MovEquipProprioPassagRoomModel): Boolean {
        try {
            movEquipProprioPassagDao.insertAll(*movEquipProprioPassagRoomModels)
        } catch (exception: Exception){
            return false
        }
        return true
    }

    override suspend fun addMovEquipProprioPassag(movEquipProprioPassagRoomModel: MovEquipProprioPassagRoomModel): Boolean {
        return movEquipProprioPassagDao.insert(movEquipProprioPassagRoomModel) > 0
    }

    override suspend fun listMovEquipProprioPassagIdMov(idMov: Long): List<MovEquipProprioPassagRoomModel> {
        return movEquipProprioPassagDao.listMovEquipProprioPassagIdMov(idMov)
    }

    override suspend fun deleteMovEquipProprioPassag(movEquipProprioPassagRoomModel: MovEquipProprioPassagRoomModel): Boolean {
        return movEquipProprioPassagDao.delete(movEquipProprioPassagRoomModel) > 0
    }

}