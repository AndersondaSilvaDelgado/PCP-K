package br.com.usinasantafe.pcp.external.room.database.variable

import br.com.usinasantafe.pcp.utils.StatusData
import br.com.usinasantafe.pcp.utils.StatusSend
import br.com.usinasantafe.pcp.external.room.dao.variable.MovEquipResidenciaDao
import br.com.usinasantafe.pcp.infra.datasource.room.variable.MovEquipResidenciaDatasourceRoom
import br.com.usinasantafe.pcp.infra.models.room.variable.MovEquipResidenciaRoomModel
import javax.inject.Inject

class MovEquipResidenciaDatasourceRoomImpl @Inject constructor (
    private val movEquipResidenciaDao: MovEquipResidenciaDao,
): MovEquipResidenciaDatasourceRoom {

    override suspend fun checkMovSend(): Boolean {
        return movEquipResidenciaDao.listMovStatusEnvio(StatusSend.SEND).isNotEmpty()
    }

    override suspend fun deleteMovEquipResidencia(movEquipResidenciaRoomModel: MovEquipResidenciaRoomModel): Boolean {
        try {
            movEquipResidenciaDao.delete(movEquipResidenciaRoomModel)
        } catch (exception: Exception){
            return false
        }
        return true
    }

    override suspend fun lastIdMovStatusStarted(): Long {
        return movEquipResidenciaDao.lastIdMovStatusEnvio(StatusSend.STARTED)
    }

    override suspend fun listMovEquipResidenciaOpen(): List<MovEquipResidenciaRoomModel> {
        return movEquipResidenciaDao.listMovStatus(StatusData.OPEN)
    }

    override suspend fun listMovEquipResidenciaStarted(): List<MovEquipResidenciaRoomModel> {
        return movEquipResidenciaDao.listMovStatusEnvio(StatusSend.STARTED)
    }

    override suspend fun listMovEquipResidenciaStartedClosed(): List<MovEquipResidenciaRoomModel> {
        return movEquipResidenciaDao.listMovStatusAndStatusEnvio(StatusData.CLOSE, StatusSend.STARTED)
    }

    override suspend fun listMovEquipResidenciaSend(): List<MovEquipResidenciaRoomModel> {
        return movEquipResidenciaDao.listMovStatusEnvio(StatusSend.SEND)
    }

    override suspend fun listMovEquipResidenciaSent(): List<MovEquipResidenciaRoomModel> {
        return movEquipResidenciaDao.listMovStatusEnvio(StatusSend.SENT)
    }

    override suspend fun updateVeiculoMovEquipResidencia(
        veiculo: String,
        movEquipVisitTercRoomModel: MovEquipResidenciaRoomModel
    ): Boolean {
        try {
            movEquipVisitTercRoomModel.veiculoMovEquipResidencia = veiculo
            movEquipResidenciaDao.update(movEquipVisitTercRoomModel)
        } catch (exception: Exception){
            return false
        }
        return true
    }

    override suspend fun updatePlacaMovEquipResidencia(
        placa: String,
        movEquipVisitTercRoomModel: MovEquipResidenciaRoomModel
    ): Boolean {
        try {
            movEquipVisitTercRoomModel.placaMovEquipResidencia = placa
            movEquipResidenciaDao.update(movEquipVisitTercRoomModel)
        } catch (exception: Exception){
            return false
        }
        return true
    }

    override suspend fun updateMotoristaMovEquipResidencia(
        motorista: String,
        movEquipVisitTercRoomModel: MovEquipResidenciaRoomModel
    ): Boolean {
        try {
            movEquipVisitTercRoomModel.motoristaMovEquipResidencia = motorista
            movEquipResidenciaDao.update(movEquipVisitTercRoomModel)
        } catch (exception: Exception){
            return false
        }
        return true
    }

    override suspend fun updateObservMovEquipResidencia(
        observ: String?,
        movEquipVisitTercRoomModel: MovEquipResidenciaRoomModel
    ): Boolean {
        try {
            movEquipVisitTercRoomModel.observMovEquipResidencia = observ
            movEquipResidenciaDao.update(movEquipVisitTercRoomModel)
        } catch (exception: Exception){
            return false
        }
        return true
    }

    override suspend fun insertMovEquipResidenciaOpen(movEquipResidenciaRoomModel: MovEquipResidenciaRoomModel): Boolean {
        try {
            movEquipResidenciaDao.insert(movEquipResidenciaRoomModel)
        } catch (exception: Exception){
            return false
        }
        return true
    }

    override suspend fun insertMovEquipResidenciaClose(movEquipResidenciaRoomModel: MovEquipResidenciaRoomModel): Boolean {
        try {
            movEquipResidenciaRoomModel.idMovEquipResidencia = null
            movEquipResidenciaRoomModel.statusMovEquipResidencia = StatusData.CLOSE
            movEquipResidenciaDao.insert(movEquipResidenciaRoomModel)
        } catch (exception: Exception){
            return false
        }
        return true
    }

    override suspend fun updateStatusSentMovEquipResidencia(idMov: Long): Boolean {
        try {
            val movEquip = movEquipResidenciaDao.listMovId(idMov).single()
            movEquip.statusSendMovEquipResidencia = StatusSend.SENT
            movEquipResidenciaDao.update(movEquip)
        } catch (exception: Exception){
            return false
        }
        return true
    }

    override suspend fun updateStatusMovEquipResidenciaClose(movEquipResidenciaRoomModel: MovEquipResidenciaRoomModel): Boolean {
        try {
            movEquipResidenciaRoomModel.statusMovEquipResidencia = StatusData.CLOSE
            movEquipResidenciaDao.update(movEquipResidenciaRoomModel)
        } catch (exception: Exception){
            return false
        }
        return true
    }

    override suspend fun updateStatusSendCloseMovEquipResidencia(movEquipResidenciaRoomModel: MovEquipResidenciaRoomModel): Boolean {
        try {
            movEquipResidenciaRoomModel.statusMovEquipResidencia = StatusData.CLOSE
            movEquipResidenciaRoomModel.statusSendMovEquipResidencia = StatusSend.SEND
            movEquipResidenciaDao.update(movEquipResidenciaRoomModel)
        } catch (exception: Exception){
            return false
        }
        return true
    }

}