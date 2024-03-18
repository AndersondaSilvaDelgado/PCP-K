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

    override suspend fun deleteMovEquipVisitTerc(movEquipVisitTercRoomModel: MovEquipVisitTercRoomModel): Boolean {
        try {
            movEquipVisitTercDao.delete(movEquipVisitTercRoomModel)
        } catch (exception: Exception){
            return false
        }
        return true
    }

    override suspend fun insertMovEquipVisitTercOpen(movEquipVisitTercRoomModel: MovEquipVisitTercRoomModel): Boolean {
        try {
            movEquipVisitTercDao.insert(movEquipVisitTercRoomModel)
        } catch (exception: Exception){
            return false
        }
        return true
    }

    override suspend fun insertMovEquipVisitTercClose(movEquipVisitTercRoomModel: MovEquipVisitTercRoomModel): Boolean {
        try {
            movEquipVisitTercRoomModel.idMovEquipVisitTerc = null
            movEquipVisitTercRoomModel.statusMovEquipVisitTerc = StatusData.CLOSE
            movEquipVisitTercDao.insert(movEquipVisitTercRoomModel)
        } catch (exception: Exception){
            return false
        }
        return true
    }

    override suspend fun lastIdMovStatusStarted(): Long {
        return movEquipVisitTercDao.lastIdMovStatusEnvio(StatusSend.STARTED)
    }

    override suspend fun listMovEquipVisitTercOpen(): List<MovEquipVisitTercRoomModel> {
        return movEquipVisitTercDao.listMovStatus(StatusData.OPEN)
    }

    override suspend fun listMovEquipVisitTercStarted(): List<MovEquipVisitTercRoomModel> {
        return movEquipVisitTercDao.listMovStatusEnvio(StatusSend.STARTED)
    }

    override suspend fun listMovEquipVisitTercSend(): List<MovEquipVisitTercRoomModel> {
        return movEquipVisitTercDao.listMovStatusEnvio(StatusSend.SEND)
    }

    override suspend fun listMovEquipVisitTercSent(): List<MovEquipVisitTercRoomModel> {
        return movEquipVisitTercDao.listMovStatusEnvio(StatusSend.SENT)
    }

    override suspend fun updateVeiculoMovEquipVisitTerc(
        veiculo: String,
        movEquipVisitTercRoomModel: MovEquipVisitTercRoomModel
    ): Boolean {
        try {
            movEquipVisitTercRoomModel.veiculoMovEquipVisitTerc = veiculo
            movEquipVisitTercDao.update(movEquipVisitTercRoomModel)
        } catch (exception: Exception){
            return false
        }
        return true
    }

    override suspend fun updatePlacaMovEquipVisitTerc(
        placa: String,
        movEquipVisitTercRoomModel: MovEquipVisitTercRoomModel
    ): Boolean {
        try {
            movEquipVisitTercRoomModel.placaMovEquipVisitTerc = placa
            movEquipVisitTercDao.update(movEquipVisitTercRoomModel)
        } catch (exception: Exception){
            return false
        }
        return true
    }

    override suspend fun updateMotoristaMovEquipVisitTerc(
        idVisitTerc: Long,
        movEquipVisitTercRoomModel: MovEquipVisitTercRoomModel
    ): Boolean {
        try {
            movEquipVisitTercRoomModel.idVisitTercMovEquipVisitTerc = idVisitTerc
            movEquipVisitTercDao.update(movEquipVisitTercRoomModel)
        } catch (exception: Exception){
            return false
        }
        return true
    }

    override suspend fun updateDestinoMovEquipVisitTerc(
        destino: String,
        movEquipVisitTercRoomModel: MovEquipVisitTercRoomModel
    ): Boolean {
        try {
            movEquipVisitTercRoomModel.destinoMovEquipVisitTerc = destino
            movEquipVisitTercDao.update(movEquipVisitTercRoomModel)
        } catch (exception: Exception){
            return false
        }
        return true
    }

    override suspend fun updateObservMovEquipVisitTerc(
        observ: String?,
        movEquipVisitTercRoomModel: MovEquipVisitTercRoomModel
    ): Boolean {
        try {
            movEquipVisitTercRoomModel.observMovEquipVisitTerc = observ
            movEquipVisitTercDao.update(movEquipVisitTercRoomModel)
        } catch (exception: Exception){
            return false
        }
        return true
    }

    override suspend fun updateStatusMovEquipVisitTercSent(idMov: Long): Boolean {
        try {
            val movEquip = movEquipVisitTercDao.listMovId(idMov).single()
            movEquip.statusSendMovEquipVisitTerc = StatusSend.SENT
            movEquipVisitTercDao.update(movEquip)
        } catch (exception: Exception){
            return false
        }
        return true
    }

    override suspend fun updateStatusMovEquipVisitTercClose(movEquipVisitTercRoomModel: MovEquipVisitTercRoomModel): Boolean {
        try {
            movEquipVisitTercRoomModel.statusMovEquipVisitTerc = StatusData.CLOSE
            movEquipVisitTercDao.update(movEquipVisitTercRoomModel)
        } catch (exception: Exception){
            return false
        }
        return true
    }

    override suspend fun updateStatusMovEquipVisitTercCloseSend(movEquipVisitTercRoomModel: MovEquipVisitTercRoomModel): Boolean {
        try {
            movEquipVisitTercRoomModel.statusMovEquipVisitTerc = StatusData.CLOSE
            movEquipVisitTercRoomModel.statusSendMovEquipVisitTerc = StatusSend.SEND
            movEquipVisitTercDao.update(movEquipVisitTercRoomModel)
        } catch (exception: Exception){
            return false
        }
        return true
    }

}