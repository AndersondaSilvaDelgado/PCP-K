package br.com.usinasantafe.pcpk.features.infra.repositories.variable

import br.com.usinasantafe.pcpk.features.domain.entities.variable.MovEquipResidencia
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipResidenciaRepository
import br.com.usinasantafe.pcpk.features.infra.datasource.room.variable.MovEquipResidenciaDatasourceRoom
import br.com.usinasantafe.pcpk.features.infra.datasource.sharedpreferences.MovEquipResidenciaDatasourceSharedPreferences
import br.com.usinasantafe.pcpk.features.infra.datasource.webservice.variable.MovEquipResidenciaDatasourceWebService
import br.com.usinasantafe.pcpk.features.infra.models.room.variable.entityToMovEquipResidenciaRoomModel
import br.com.usinasantafe.pcpk.features.infra.models.room.variable.modelRoomToMovEquipResidencia
import br.com.usinasantafe.pcpk.features.infra.models.sharedpreferences.modelSharedPreferencesToMovEquipResidencia
import br.com.usinasantafe.pcpk.features.infra.models.webservice.entityToMovEquipResidenciaWebServiceModel
import br.com.usinasantafe.pcpk.features.infra.models.webservice.modelWebServiceToMovEquipResidencia
import javax.inject.Inject

class MovEquipResidenciaRepositoryImpl @Inject constructor (
    private val movEquipResidenciaDatasourceRoom: MovEquipResidenciaDatasourceRoom,
    private val movEquipResidenciaDatasourceSharedPreferences: MovEquipResidenciaDatasourceSharedPreferences,
    private val movEquipResidenciaDatasourceWebService: MovEquipResidenciaDatasourceWebService,
) : MovEquipResidenciaRepository {

    override suspend fun checkMovSend(): Boolean {
        return movEquipResidenciaDatasourceRoom.checkMovSend()
    }

    override suspend fun listMovEquipResidenciaOpen(): List<MovEquipResidencia> {
        return movEquipResidenciaDatasourceRoom.listMovEquipResidenciaOpen().map { it.modelRoomToMovEquipResidencia() }
    }

    override suspend fun listMovEquipResidenciaStarted(): List<MovEquipResidencia> {
        return movEquipResidenciaDatasourceRoom.listMovEquipResidenciaEmpty().map { it.modelRoomToMovEquipResidencia() }
    }

    override suspend fun listMovEquipResidenciaSend(): List<MovEquipResidencia> {
        return movEquipResidenciaDatasourceRoom.listMovEquipResidenciaSend().map { it.modelRoomToMovEquipResidencia() }
    }

    override suspend fun receiverSentMovEquipResidencia(movEquipList: List<MovEquipResidencia>): Boolean {
        for(movEquip in movEquipList){
            if(!movEquipResidenciaDatasourceRoom.updateStatusSentMovEquipResidencia(movEquip.idMovEquipResidencia!!)) return false
        }
        return true
    }

    override suspend fun saveMovEquipResidencia(matricVigia: Long, idLocal: Long): Long {
        try {
            val movEquipResidencia = movEquipResidenciaDatasourceSharedPreferences.getMovEquipResidencia().modelSharedPreferencesToMovEquipResidencia()
            val movEquipResidenciaRoomModel = movEquipResidencia.entityToMovEquipResidenciaRoomModel(matricVigia, idLocal)
            if(!movEquipResidenciaDatasourceRoom.insertMovEquipResidenciaOpen(movEquipResidenciaRoomModel)) return 0L
            if(!movEquipResidenciaDatasourceSharedPreferences.clearMovEquipResidencia()) return 0L
            return movEquipResidenciaDatasourceRoom.lastIdMovStatusSend()
        } catch (exception: Exception){
            return 0
        }
    }

    override suspend fun saveMovEquipResidencia(
        matricVigia: Long,
        idLocal: Long,
        movEquipResidencia: MovEquipResidencia
    ): Long {
        try {
            val movEquipResidenciaRoomModel = movEquipResidencia.entityToMovEquipResidenciaRoomModel(matricVigia, idLocal)
            if(!movEquipResidenciaDatasourceRoom.insertMovEquipResidenciaClose(movEquipResidenciaRoomModel)) return 0
            if(!movEquipResidenciaDatasourceSharedPreferences.clearMovEquipResidencia()) return 0
            return movEquipResidenciaDatasourceRoom.lastIdMovStatusSend()
        } catch (exception: Exception){
            return 0
        }
    }

    override suspend fun sendMovEquipResidencia(
        movEquipList: List<MovEquipResidencia>,
        nroAparelho: Long
    ): Result<List<MovEquipResidencia>> {
        val result = movEquipResidenciaDatasourceWebService.sendMovEquipResidencia(movEquipList.map { it.entityToMovEquipResidenciaWebServiceModel(nroAparelho) }, nroAparelho)
        if(result.isSuccess) {
            return result.map { listMovEquip ->
                listMovEquip.map { movEquip ->
                    movEquip.modelWebServiceToMovEquipResidencia()
                }
            }
        }
        return Result.failure(Throwable(result.exceptionOrNull()))
    }

    override suspend fun setMotoristaMovEquipResidencia(motorista: String): Boolean {
        return try {
            movEquipResidenciaDatasourceSharedPreferences.setMotoristaMovEquipResidencia(motorista)
        } catch (exception: Exception){
            false
        }
    }

    override suspend fun setObservMovEquipResidencia(observ: String?): Boolean {
        return try {
            movEquipResidenciaDatasourceSharedPreferences.setObservMovEquipResidencia(observ)
        } catch (exception: Exception){
            false
        }
    }

    override suspend fun setPlacaMovEquipResidencia(placa: String): Boolean {
        return try {
            movEquipResidenciaDatasourceSharedPreferences.setPlacaMovEquipResidencia(placa)
        } catch (exception: Exception){
            false
        }
    }

    override suspend fun setStatusCloseMov(movEquipResidencia: MovEquipResidencia): Boolean {
        return try {
            movEquipResidenciaDatasourceRoom.updateStatusCloseMovEquipResidencia(
                movEquipResidencia.entityToMovEquipResidenciaRoomModel(
                    movEquipResidencia.nroMatricVigiaMovEquipResidencia!!,
                    movEquipResidencia.idLocalMovEquipResidencia!!
                )
            )
        } catch (exception: Exception) {
            false
        }
    }

    override suspend fun setStatusSendCloseMov(movEquipResidencia: MovEquipResidencia): Boolean {
        return try {
            movEquipResidenciaDatasourceRoom.updateStatusSendCloseMovEquipResidencia(
                movEquipResidencia.entityToMovEquipResidenciaRoomModel(
                    movEquipResidencia.nroMatricVigiaMovEquipResidencia!!,
                    movEquipResidencia.idLocalMovEquipResidencia!!
                )
            )
        } catch (exception: Exception) {
            false
        }
    }

    override suspend fun setVeiculoMovEquipResidencia(veiculo: String): Boolean {
        return try {
            movEquipResidenciaDatasourceSharedPreferences.setVeiculoMovEquipResidencia(veiculo)
        } catch (exception: Exception){
            false
        }
    }

    override suspend fun startMovEquipResidencia(): Boolean {
        return try {
            movEquipResidenciaDatasourceSharedPreferences.startMovEquipResidencia()
        } catch (exception: Exception){
            false
        }
    }

    override suspend fun updateVeiculoMovEquipResidencia(
        veiculo: String,
        movEquipResidencia: MovEquipResidencia
    ): Boolean {
        return try {
            movEquipResidenciaDatasourceRoom.updateVeiculoMovEquipResidencia(
                veiculo,
                movEquipResidencia.entityToMovEquipResidenciaRoomModel(
                    movEquipResidencia.nroMatricVigiaMovEquipResidencia!!,
                    movEquipResidencia.idLocalMovEquipResidencia!!
                )
            )
        } catch (exception: Exception) {
            false
        }
    }

    override suspend fun updatePlacaMovEquipResidencia(
        placa: String,
        movEquipResidencia: MovEquipResidencia
    ): Boolean {
        return try {
            movEquipResidenciaDatasourceRoom.updatePlacaMovEquipResidencia(
                placa,
                movEquipResidencia.entityToMovEquipResidenciaRoomModel(
                    movEquipResidencia.nroMatricVigiaMovEquipResidencia!!,
                    movEquipResidencia.idLocalMovEquipResidencia!!
                )
            )
        } catch (exception: Exception) {
            false
        }
    }

    override suspend fun updateMotoristaMovEquipResidencia(
        motorista: String,
        movEquipResidencia: MovEquipResidencia
    ): Boolean {
        return try {
            movEquipResidenciaDatasourceRoom.updateMotoristaMovEquipResidencia(
                motorista,
                movEquipResidencia.entityToMovEquipResidenciaRoomModel(
                    movEquipResidencia.nroMatricVigiaMovEquipResidencia!!,
                    movEquipResidencia.idLocalMovEquipResidencia!!
                )
            )
        } catch (exception: Exception) {
            false
        }
    }

    override suspend fun updateObservMovEquipResidencia(
        observ: String?,
        movEquipResidencia: MovEquipResidencia
    ): Boolean {
        return try {
            movEquipResidenciaDatasourceRoom.updateObservMovEquipResidencia(
                observ,
                movEquipResidencia.entityToMovEquipResidenciaRoomModel(
                    movEquipResidencia.nroMatricVigiaMovEquipResidencia!!,
                    movEquipResidencia.idLocalMovEquipResidencia!!
                )
            )
        } catch (exception: Exception) {
            false
        }
    }

}