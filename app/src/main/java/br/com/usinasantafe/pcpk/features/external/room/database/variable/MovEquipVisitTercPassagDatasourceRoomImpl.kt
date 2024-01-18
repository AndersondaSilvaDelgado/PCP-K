package br.com.usinasantafe.pcpk.features.external.room.database.variable

import br.com.usinasantafe.pcpk.features.external.room.dao.variable.MovEquipVisitTercPassagDao
import br.com.usinasantafe.pcpk.features.infra.datasource.room.variable.MovEquipVisitTercPassagDatasourceRoom
import br.com.usinasantafe.pcpk.features.infra.models.room.variable.MovEquipVisitTercPassagRoomModel
import javax.inject.Inject

class MovEquipVisitTercPassagDatasourceRoomImpl @Inject constructor (
    private val movEquipVisitTercPassagDao: MovEquipVisitTercPassagDao,
): MovEquipVisitTercPassagDatasourceRoom {

    override suspend fun addAllMovEquipVisitTercPassag(vararg movEquipVisitTercPassagRoomModels: MovEquipVisitTercPassagRoomModel): Boolean {
        try {
            movEquipVisitTercPassagDao.insertAll(*movEquipVisitTercPassagRoomModels)
        } catch (exception: Exception){
            return false
        }
        return true
    }

    override suspend fun addMovEquipVisitTercPassag(movEquipVisitTercPassagRoomModel: MovEquipVisitTercPassagRoomModel): Boolean {
        return movEquipVisitTercPassagDao.insert(movEquipVisitTercPassagRoomModel) > 0
    }

    override suspend fun deleteMovEquipVisitTercPassag(movEquipVisitTercPassagRoomModel: MovEquipVisitTercPassagRoomModel): Boolean {
        return movEquipVisitTercPassagDao.delete(movEquipVisitTercPassagRoomModel) > 0
    }

    override suspend fun listMovEquipVisitTercPassagIdMov(idMov: Long): List<MovEquipVisitTercPassagRoomModel> {
        return movEquipVisitTercPassagDao.listMovEquipVisitTercPassagIdMov(idMov)
    }

}