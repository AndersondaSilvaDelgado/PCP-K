package br.com.usinasantafe.pcpk.features.external.room.database.variable

import br.com.usinasantafe.pcpk.common.utils.StatusData
import br.com.usinasantafe.pcpk.common.utils.StatusSend
import br.com.usinasantafe.pcpk.features.external.room.dao.variable.MovEquipProprioDao
import br.com.usinasantafe.pcpk.features.infra.datasource.room.variable.MovEquipProprioDatasourceRoom
import br.com.usinasantafe.pcpk.features.infra.models.room.variable.MovEquipProprioRoomModel
import javax.inject.Inject

class MovEquipProprioDatasourceRoomImpl @Inject constructor(
    private val movEquipProprioDao: MovEquipProprioDao
) : MovEquipProprioDatasourceRoom {

    override suspend fun checkMovSend(): Boolean {
        return movEquipProprioDao.listMovStatusEnvio(StatusSend.SEND).isNotEmpty()
    }

    override suspend fun updateStatusMovEquipProprioCloseSend(movEquipProprioRoomModel: MovEquipProprioRoomModel): Boolean {
        return try {
            movEquipProprioRoomModel.statusMovEquipProprio = StatusData.CLOSE
            movEquipProprioRoomModel.statusSendMovEquipProprio = StatusSend.SEND
            return movEquipProprioDao.update(movEquipProprioRoomModel) > 0
        } catch (exception: Exception) {
            false
        }
    }

    override suspend fun lastIdMovStatusSend(): Int {
        return movEquipProprioDao.lastIdMovStatusEnvio(StatusSend.SEND)
    }

    override suspend fun listMovEquipProprioOpen(): List<MovEquipProprioRoomModel> {
        return movEquipProprioDao.listMovStatus(StatusData.OPEN)
    }

    override suspend fun listMovEquipProprioEmpty(): List<MovEquipProprioRoomModel> {
        return movEquipProprioDao.listMovStatusEnvio(StatusSend.STARTED)
    }

    override suspend fun listMovEquipProprioSend(): List<MovEquipProprioRoomModel> {
        return movEquipProprioDao.listMovStatusEnvio(StatusSend.SEND)
    }

    override suspend fun saveMovEquipProprioOpen(movEquipProprioRoomModel: MovEquipProprioRoomModel): Boolean {
        return movEquipProprioDao.insert(movEquipProprioRoomModel) > 0
    }

    override suspend fun updateStatusMovEquipProprioSent(idMov: Long): Boolean {
        return try {
            val movEquip = movEquipProprioDao.listMovId(idMov).single()
            movEquip.statusSendMovEquipProprio = StatusSend.SENT
            return movEquipProprioDao.update(movEquip) > 0
        } catch (exception: Exception) {
            false
        }
    }

    override suspend fun updateDestinoMovEquipProprio(
        destino: String,
        movEquipProprioRoomModel: MovEquipProprioRoomModel
    ): Boolean {
        movEquipProprioRoomModel.destinoMovEquipProprio = destino
        return movEquipProprioDao.update(movEquipProprioRoomModel) > 0
    }

    override suspend fun updateIdEquipMovEquipProprio(
        idEquip: Long,
        movEquipProprioRoomModel: MovEquipProprioRoomModel
    ): Boolean {
        movEquipProprioRoomModel.idEquipMovEquipProprio = idEquip
        return movEquipProprioDao.update(movEquipProprioRoomModel) > 0
    }

    override suspend fun updateNroColabMovEquipProprio(
        nroMatric: Long,
        movEquipProprioRoomModel: MovEquipProprioRoomModel
    ): Boolean {
        movEquipProprioRoomModel.nroMatricColabMovEquipProprio = nroMatric
        return movEquipProprioDao.update(movEquipProprioRoomModel) > 0
    }

    override suspend fun updateNotaFiscalMovEquipProprio(
        notaFiscal: Long,
        movEquipProprioRoomModel: MovEquipProprioRoomModel
    ): Boolean {
        movEquipProprioRoomModel.nroNotaFiscalMovEquipProprio = notaFiscal
        return movEquipProprioDao.update(movEquipProprioRoomModel) > 0
    }

}