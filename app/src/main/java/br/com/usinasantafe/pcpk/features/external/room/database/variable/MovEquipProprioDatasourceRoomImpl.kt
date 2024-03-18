package br.com.usinasantafe.pcpk.features.external.room.database.variable

import android.util.Log
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

    override suspend fun deleteMov(movEquipProprioRoomModel: MovEquipProprioRoomModel): Boolean {
        try {
            movEquipProprioDao.delete(movEquipProprioRoomModel)
        } catch (exception: Exception) {
            return false
        }
        return true
    }

    override suspend fun updateStatusMovEquipProprioCloseSend(movEquipProprioRoomModel: MovEquipProprioRoomModel): Boolean {
        try {
            movEquipProprioRoomModel.statusSendMovEquipProprio = StatusSend.SEND
            movEquipProprioDao.update(movEquipProprioRoomModel)
        } catch (exception: Exception) {
            return false
        }
        return true
    }

    override suspend fun lastIdMovStatusStarted(): Long {
        return movEquipProprioDao.lastIdMovStatusEnvio(StatusSend.STARTED)
    }

    override suspend fun listMovEquipProprioStarted(): List<MovEquipProprioRoomModel> {
        return movEquipProprioDao.listMovStatusEnvio(StatusSend.STARTED)
    }

    override suspend fun listMovEquipProprioSend(): List<MovEquipProprioRoomModel> {
        return movEquipProprioDao.listMovStatusEnvio(StatusSend.SEND)
    }

    override suspend fun listMovEquipProprioSent(): List<MovEquipProprioRoomModel> {
        return movEquipProprioDao.listMovStatusEnvio(StatusSend.SENT)
    }

    override suspend fun saveMovEquipProprioOpen(movEquipProprioRoomModel: MovEquipProprioRoomModel): Boolean {
        try {
            movEquipProprioDao.insert(movEquipProprioRoomModel)
        } catch (exception: Exception) {
            return false
        }
        return true
    }

    override suspend fun updateStatusMovEquipProprioSent(idMov: Long): Boolean {
        try {
            val movEquip = movEquipProprioDao.listMovId(idMov).single()
            movEquip.statusSendMovEquipProprio = StatusSend.SENT
            movEquipProprioDao.update(movEquip)
        } catch (exception: Exception) {
            return false
        }
        return true
    }

    override suspend fun updateDestinoMovEquipProprio(
        destino: String,
        movEquipProprioRoomModel: MovEquipProprioRoomModel
    ): Boolean {
        try {
            movEquipProprioRoomModel.destinoMovEquipProprio = destino
            movEquipProprioDao.update(movEquipProprioRoomModel)
        } catch (exception: Exception) {
            return false
        }
        return true
    }

    override suspend fun updateIdEquipMovEquipProprio(
        idEquip: Long,
        movEquipProprioRoomModel: MovEquipProprioRoomModel
    ): Boolean {
        try {
            movEquipProprioRoomModel.idEquipMovEquipProprio = idEquip
            movEquipProprioDao.update(movEquipProprioRoomModel)
        } catch (exception: Exception) {
            return false
        }
        return true
    }

    override suspend fun updateNroColabMovEquipProprio(
        nroMatric: Long,
        movEquipProprioRoomModel: MovEquipProprioRoomModel
    ): Boolean {
        try {
            movEquipProprioRoomModel.nroMatricColabMovEquipProprio = nroMatric
            movEquipProprioDao.update(movEquipProprioRoomModel)
        } catch (exception: Exception) {
            return false
        }
        return true
    }

    override suspend fun updateNotaFiscalMovEquipProprio(
        notaFiscal: Long,
        movEquipProprioRoomModel: MovEquipProprioRoomModel
    ): Boolean {
        try {
            movEquipProprioRoomModel.nroNotaFiscalMovEquipProprio = notaFiscal
            movEquipProprioDao.update(movEquipProprioRoomModel)
        } catch (exception: Exception) {
            return false
        }
        return true
    }

    override suspend fun updateObservMovEquipProprio(
        observ: String?,
        movEquipProprioRoomModel: MovEquipProprioRoomModel
    ): Boolean {
        try {
            movEquipProprioRoomModel.observMovEquipProprio = observ
            movEquipProprioDao.update(movEquipProprioRoomModel)
        } catch (exception: Exception) {
            return false
        }
        return true
    }

}