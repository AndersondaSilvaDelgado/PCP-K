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

    override suspend fun listMovEquipVisitTercStarted(): List<MovEquipVisitTercRoomModel> {
        return movEquipVisitTercDao.listMovStatusEnvio(StatusSend.STARTED)
    }

    override suspend fun listMovEquipVisitTercSend(): List<MovEquipVisitTercRoomModel> {
        return movEquipVisitTercDao.listMovStatusEnvio(StatusSend.SEND)
    }

    override suspend fun updateVeiculoMovEquipVisitTerc(
        veiculo: String,
        movEquipVisitTercRoomModel: MovEquipVisitTercRoomModel
    ): Boolean {
        movEquipVisitTercRoomModel.veiculoMovEquipVisitTerc = veiculo
        return movEquipVisitTercDao.update(movEquipVisitTercRoomModel) > 0
    }

    override suspend fun updatePlacaMovEquipVisitTerc(
        placa: String,
        movEquipVisitTercRoomModel: MovEquipVisitTercRoomModel
    ): Boolean {
        movEquipVisitTercRoomModel.placaMovEquipVisitTerc = placa
        return movEquipVisitTercDao.update(movEquipVisitTercRoomModel) > 0
    }

    override suspend fun updateMotoristaMovEquipVisitTerc(
        idVisitTerc: Long,
        movEquipVisitTercRoomModel: MovEquipVisitTercRoomModel
    ): Boolean {
        movEquipVisitTercRoomModel.idVisitTercMovEquipVisitTerc = idVisitTerc
        return movEquipVisitTercDao.update(movEquipVisitTercRoomModel) > 0
    }

    override suspend fun updateDestinoMovEquipVisitTerc(
        destino: String,
        movEquipVisitTercRoomModel: MovEquipVisitTercRoomModel
    ): Boolean {
        movEquipVisitTercRoomModel.destinoMovEquipVisitTerc = destino
        return movEquipVisitTercDao.update(movEquipVisitTercRoomModel) > 0
    }

    override suspend fun updateObservMovEquipVisitTerc(
        observ: String?,
        movEquipVisitTercRoomModel: MovEquipVisitTercRoomModel
    ): Boolean {
        movEquipVisitTercRoomModel.observMovEquipVisitTerc = observ
        return movEquipVisitTercDao.update(movEquipVisitTercRoomModel) > 0
    }

    override suspend fun updateStatusMovEquipVisitTercSent(idMov: Long): Boolean {
        return try{
            val movEquip = movEquipVisitTercDao.listMovId(idMov).single()
            movEquip.statusSendMovEquipVisitTerc = StatusSend.SENT
            return movEquipVisitTercDao.update(movEquip) > 0
        } catch (exception: Exception){
            false
        }
    }

    override suspend fun updateStatusMovEquipVisitTercClose(movEquipVisitTercRoomModel: MovEquipVisitTercRoomModel): Boolean {
        return try{
            movEquipVisitTercRoomModel.statusMovEquipVisitTerc = StatusData.CLOSE
            return movEquipVisitTercDao.update(movEquipVisitTercRoomModel) > 0
        } catch (exception: Exception){
            false
        }
    }

    override suspend fun updateStatusMovEquipVisitTercCloseSend(movEquipVisitTercRoomModel: MovEquipVisitTercRoomModel): Boolean {
        return try{
            movEquipVisitTercRoomModel.statusMovEquipVisitTerc = StatusData.CLOSE
            movEquipVisitTercRoomModel.statusSendMovEquipVisitTerc = StatusSend.SEND
            return movEquipVisitTercDao.update(movEquipVisitTercRoomModel) > 0
        } catch (exception: Exception){
            false
        }
    }

}