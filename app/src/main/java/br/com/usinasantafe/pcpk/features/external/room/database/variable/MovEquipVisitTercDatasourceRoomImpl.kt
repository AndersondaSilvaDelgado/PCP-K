package br.com.usinasantafe.pcpk.features.external.room.database.variable

import br.com.usinasantafe.pcpk.common.utils.StatusData
import br.com.usinasantafe.pcpk.common.utils.StatusSend
import br.com.usinasantafe.pcpk.features.external.room.dao.variable.MovEquipVisitTercDao
import br.com.usinasantafe.pcpk.features.infra.datasource.room.variable.MovEquipVisitTercDatasourceRoom
import br.com.usinasantafe.pcpk.features.infra.models.room.variable.MovEquipVisitTercRoomModel
import javax.inject.Inject

class MovEquipVisitTercDatasourceRoomImpl @Inject constructor (
    private val movEquipVisitTercDao: MovEquipVisitTercDao,
): MovEquipVisitTercDatasourceRoom {

    override suspend fun checkMovSend(): Boolean {
        return movEquipVisitTercDao.listMovStatusEnvio(StatusSend.SEND).isNotEmpty()
    }

    override suspend fun insertMovEquipVisitTercOpen(movEquipVisitTercRoomModel: MovEquipVisitTercRoomModel): Boolean {
        return movEquipVisitTercDao.insert(movEquipVisitTercRoomModel) > 0
    }

    override suspend fun insertMovEquipVisitTercClose(movEquipVisitTercRoomModel: MovEquipVisitTercRoomModel): Boolean {
        movEquipVisitTercRoomModel.statusMovEquipVisitTerc = StatusData.CLOSE
        return movEquipVisitTercDao.insert(movEquipVisitTercRoomModel) > 0
    }

    override suspend fun lastIdMovStatusSend(): Int {
        return movEquipVisitTercDao.lastIdMovStatusEnvio(StatusSend.SEND)
    }

    override suspend fun listMovEquipVisitTercOpen(): List<MovEquipVisitTercRoomModel> {
        return movEquipVisitTercDao.listMovStatus(StatusData.OPEN)
    }

    override suspend fun listMovEquipVisitTercEmpty(): List<MovEquipVisitTercRoomModel> {
        return movEquipVisitTercDao.listMovStatusEnvio(StatusSend.EMPTY)
    }

    override suspend fun listMovEquipVisitTercSend(): List<MovEquipVisitTercRoomModel> {
        return movEquipVisitTercDao.listMovStatusEnvio(StatusSend.SEND)
    }

    override suspend fun updateMovEquipVisitTercSent(idMov: Long): Boolean {
        return try{
            val movEquip = movEquipVisitTercDao.listMovId(idMov).single()
            movEquip.statusSendMovEquipVisitTerc = StatusSend.SENT
            return movEquipVisitTercDao.update(movEquip) > 0
        } catch (exception: Exception){
            false
        }
    }

    override suspend fun updateMovEquipVisitTercClose(movEquipVisitTercRoomModel: MovEquipVisitTercRoomModel): Boolean {
        return try{
            movEquipVisitTercRoomModel.statusMovEquipVisitTerc = StatusData.CLOSE
            return movEquipVisitTercDao.update(movEquipVisitTercRoomModel) > 0
        } catch (exception: Exception){
            false
        }
    }

    override suspend fun updateMovEquipVisitTercCloseSend(movEquipVisitTercRoomModel: MovEquipVisitTercRoomModel): Boolean {
        return try{
            movEquipVisitTercRoomModel.statusMovEquipVisitTerc = StatusData.CLOSE
            movEquipVisitTercRoomModel.statusSendMovEquipVisitTerc = StatusSend.SEND
            return movEquipVisitTercDao.update(movEquipVisitTercRoomModel) > 0
        } catch (exception: Exception){
            false
        }
    }

}