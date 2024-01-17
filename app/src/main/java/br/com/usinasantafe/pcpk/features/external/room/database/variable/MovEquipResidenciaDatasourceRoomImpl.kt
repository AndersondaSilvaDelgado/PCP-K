package br.com.usinasantafe.pcpk.features.external.room.database.variable

import br.com.usinasantafe.pcpk.common.utils.StatusData
import br.com.usinasantafe.pcpk.common.utils.StatusSend
import br.com.usinasantafe.pcpk.features.external.room.dao.variable.MovEquipResidenciaDao
import br.com.usinasantafe.pcpk.features.infra.datasource.room.variable.MovEquipResidenciaDatasourceRoom
import br.com.usinasantafe.pcpk.features.infra.models.room.variable.MovEquipResidenciaRoomModel
import javax.inject.Inject

class MovEquipResidenciaDatasourceRoomImpl @Inject constructor (
    private val movEquipResidenciaDao: MovEquipResidenciaDao,
): MovEquipResidenciaDatasourceRoom {

    override suspend fun checkMovSend(): Boolean {
        return movEquipResidenciaDao.listMovStatusEnvio(StatusSend.SEND).isNotEmpty()
    }

    override suspend fun lastIdMovStatusSend(): Int {
        return movEquipResidenciaDao.lastIdMovStatusEnvio(StatusSend.SEND)
    }

    override suspend fun listMovEquipResidenciaOpen(): List<MovEquipResidenciaRoomModel> {
        return movEquipResidenciaDao.listMovStatus(StatusData.OPEN)
    }

    override suspend fun listMovEquipResidenciaEmpty(): List<MovEquipResidenciaRoomModel> {
        return movEquipResidenciaDao.listMovStatusEnvio(StatusSend.EMPTY)
    }

    override suspend fun listMovEquipResidenciaSend(): List<MovEquipResidenciaRoomModel> {
        return movEquipResidenciaDao.listMovStatusEnvio(StatusSend.SEND)
    }

    override suspend fun insertMovEquipResidenciaOpen(movEquipResidenciaRoomModel: MovEquipResidenciaRoomModel): Boolean {
        return movEquipResidenciaDao.insert(movEquipResidenciaRoomModel) > 0
    }

    override suspend fun insertMovEquipResidenciaClose(movEquipResidenciaRoomModel: MovEquipResidenciaRoomModel): Boolean {
        movEquipResidenciaRoomModel.statusMovEquipResidencia = StatusData.CLOSE
        return movEquipResidenciaDao.insert(movEquipResidenciaRoomModel) > 0
    }

    override suspend fun updateMovEquipResidenciaSent(idMov: Long): Boolean {
        return try{
            val movEquip = movEquipResidenciaDao.listMovId(idMov).single()
            movEquip.statusSendMovEquipResidencia = StatusSend.SENT
            return movEquipResidenciaDao.update(movEquip) > 0
        } catch (exception: Exception){
            false
        }
    }

    override suspend fun updateMovEquipResidenciaClose(movEquipResidenciaRoomModel: MovEquipResidenciaRoomModel): Boolean {
        return try{
            movEquipResidenciaRoomModel.statusMovEquipResidencia = StatusData.CLOSE
            return movEquipResidenciaDao.update(movEquipResidenciaRoomModel) > 0
        } catch (exception: Exception){
            false
        }
    }

    override suspend fun updateMovEquipResidenciaSendClose(movEquipResidenciaRoomModel: MovEquipResidenciaRoomModel): Boolean {
        return try{
            movEquipResidenciaRoomModel.statusMovEquipResidencia = StatusData.CLOSE
            movEquipResidenciaRoomModel.statusSendMovEquipResidencia = StatusSend.SEND
            return movEquipResidenciaDao.update(movEquipResidenciaRoomModel) > 0
        } catch (exception: Exception){
            false
        }
    }

}