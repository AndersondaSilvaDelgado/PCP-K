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
        return movEquipResidenciaDao.listMovStatusEnvio(StatusSend.STARTED)
    }

    override suspend fun listMovEquipResidenciaSend(): List<MovEquipResidenciaRoomModel> {
        return movEquipResidenciaDao.listMovStatusEnvio(StatusSend.SEND)
    }

    override suspend fun updateVeiculoMovEquipResidencia(
        veiculo: String,
        movEquipVisitTercRoomModel: MovEquipResidenciaRoomModel
    ): Boolean {
        movEquipVisitTercRoomModel.veiculoMovEquipResidencia = veiculo
        return movEquipResidenciaDao.update(movEquipVisitTercRoomModel) > 0
    }

    override suspend fun updatePlacaMovEquipResidencia(
        placa: String,
        movEquipVisitTercRoomModel: MovEquipResidenciaRoomModel
    ): Boolean {
        movEquipVisitTercRoomModel.placaMovEquipResidencia = placa
        return movEquipResidenciaDao.update(movEquipVisitTercRoomModel) > 0
    }

    override suspend fun updateMotoristaMovEquipResidencia(
        motorista: String,
        movEquipVisitTercRoomModel: MovEquipResidenciaRoomModel
    ): Boolean {
        movEquipVisitTercRoomModel.motoristaMovEquipResidencia = motorista
        return movEquipResidenciaDao.update(movEquipVisitTercRoomModel) > 0
    }

    override suspend fun updateObservMovEquipResidencia(
        observ: String?,
        movEquipVisitTercRoomModel: MovEquipResidenciaRoomModel
    ): Boolean {
        movEquipVisitTercRoomModel.observMovEquipResidencia = observ
        return movEquipResidenciaDao.update(movEquipVisitTercRoomModel) > 0
    }

    override suspend fun insertMovEquipResidenciaOpen(movEquipResidenciaRoomModel: MovEquipResidenciaRoomModel): Boolean {
        return movEquipResidenciaDao.insert(movEquipResidenciaRoomModel) > 0
    }

    override suspend fun insertMovEquipResidenciaClose(movEquipResidenciaRoomModel: MovEquipResidenciaRoomModel): Boolean {
        movEquipResidenciaRoomModel.statusMovEquipResidencia = StatusData.CLOSE
        return movEquipResidenciaDao.insert(movEquipResidenciaRoomModel) > 0
    }

    override suspend fun updateStatusSentMovEquipResidencia(idMov: Long): Boolean {
        return try{
            val movEquip = movEquipResidenciaDao.listMovId(idMov).single()
            movEquip.statusSendMovEquipResidencia = StatusSend.SENT
            return movEquipResidenciaDao.update(movEquip) > 0
        } catch (exception: Exception){
            false
        }
    }

    override suspend fun updateStatusCloseMovEquipResidencia(movEquipResidenciaRoomModel: MovEquipResidenciaRoomModel): Boolean {
        return try{
            movEquipResidenciaRoomModel.statusMovEquipResidencia = StatusData.CLOSE
            return movEquipResidenciaDao.update(movEquipResidenciaRoomModel) > 0
        } catch (exception: Exception){
            false
        }
    }

    override suspend fun updateStatusSendCloseMovEquipResidencia(movEquipResidenciaRoomModel: MovEquipResidenciaRoomModel): Boolean {
        return try{
            movEquipResidenciaRoomModel.statusMovEquipResidencia = StatusData.CLOSE
            movEquipResidenciaRoomModel.statusSendMovEquipResidencia = StatusSend.SEND
            return movEquipResidenciaDao.update(movEquipResidenciaRoomModel) > 0
        } catch (exception: Exception){
            false
        }
    }

}