package br.com.usinasantafe.pcp.external.room.database.variable

import br.com.usinasantafe.pcp.external.room.dao.variable.MovEquipVisitTercPassagDao
import br.com.usinasantafe.pcp.infra.datasource.room.variable.MovEquipVisitTercPassagDatasourceRoom
import br.com.usinasantafe.pcp.infra.models.room.variable.MovEquipVisitTercPassagRoomModel
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
        try {
            movEquipVisitTercPassagDao.insert(movEquipVisitTercPassagRoomModel)
        } catch (exception: Exception){
            return false
        }
        return true
    }

    override suspend fun deleteMovEquipVisitTercPassag(movEquipVisitTercPassagRoomModel: MovEquipVisitTercPassagRoomModel): Boolean {
        try {
            movEquipVisitTercPassagDao.delete(movEquipVisitTercPassagRoomModel)
        } catch (exception: Exception){
            return false
        }
        return true
    }

    override suspend fun listMovEquipVisitTercPassagIdMov(idMov: Long): List<MovEquipVisitTercPassagRoomModel> {
        return movEquipVisitTercPassagDao.listMovEquipVisitTercPassagIdMov(idMov)
    }

}