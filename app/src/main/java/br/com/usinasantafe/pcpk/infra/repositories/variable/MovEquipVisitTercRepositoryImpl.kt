package br.com.usinasantafe.pcpk.infra.repositories.variable

import br.com.usinasantafe.pcpk.utils.TypeVisitTerc
import br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipVisitTerc
import br.com.usinasantafe.pcpk.domain.repositories.variable.MovEquipVisitTercRepository
import br.com.usinasantafe.pcpk.infra.datasource.room.variable.MovEquipVisitTercDatasourceRoom
import br.com.usinasantafe.pcpk.infra.datasource.sharedpreferences.MovEquipVisitTercDatasourceSharedPreferences
import br.com.usinasantafe.pcpk.infra.datasource.webservice.variable.MovEquipVisitTercDatasourceWebService
import br.com.usinasantafe.pcpk.infra.models.room.variable.entityToMovEquipVisitTercRoomModel
import br.com.usinasantafe.pcpk.infra.models.room.variable.modelRoomToMovEquipVisitTerc
import br.com.usinasantafe.pcpk.infra.models.sharedpreferences.modelSharedPreferencesToMovEquipVisitTerc
import br.com.usinasantafe.pcpk.infra.models.webservice.entityToMovEquipVisitTercWebServiceModel
import br.com.usinasantafe.pcpk.infra.models.webservice.modelWebServiceToMovEquipVisitTerc
import javax.inject.Inject

class MovEquipVisitTercRepositoryImpl @Inject constructor (
    private val movEquipVisitTercDatasourceRoom: MovEquipVisitTercDatasourceRoom,
    private val movEquipVisitTercDatasourceSharedPreferences: MovEquipVisitTercDatasourceSharedPreferences,
    private val movEquipVisitTercDatasourceWebService: MovEquipVisitTercDatasourceWebService,
) : MovEquipVisitTercRepository {

    override suspend fun checkMovSend(): Boolean {
        return movEquipVisitTercDatasourceRoom.checkMovSend()
    }

    override suspend fun deleteMovEquipVisitTerc(movEquipVisitTerc: br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipVisitTerc): Boolean {
        try {
            movEquipVisitTercDatasourceRoom.deleteMovEquipVisitTerc(
                movEquipVisitTerc.entityToMovEquipVisitTercRoomModel(
                    movEquipVisitTerc.nroMatricVigiaMovEquipVisitTerc!!,
                    movEquipVisitTerc.idLocalMovEquipVisitTerc!!
                )
            )
        } catch (exception: Exception) {
            return false
        }
        return true
    }

    override suspend fun setStatusCloseMov(movEquipVisitTerc: br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipVisitTerc): Boolean {
        try {
            movEquipVisitTercDatasourceRoom.updateStatusMovEquipVisitTercClose(
                movEquipVisitTerc.entityToMovEquipVisitTercRoomModel(
                    movEquipVisitTerc.nroMatricVigiaMovEquipVisitTerc!!,
                    movEquipVisitTerc.idLocalMovEquipVisitTerc!!
                )
            )
        } catch (exception: Exception) {
            return false
        }
        return true
    }

    override suspend fun setStatusSendMov(movEquipVisitTerc: br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipVisitTerc): Boolean {
        return try {
            movEquipVisitTercDatasourceRoom.updateStatusMovEquipVisitTercCloseSend(
                movEquipVisitTerc.entityToMovEquipVisitTercRoomModel(
                    movEquipVisitTerc.nroMatricVigiaMovEquipVisitTerc!!,
                    movEquipVisitTerc.idLocalMovEquipVisitTerc!!
                )
            )
        } catch (exception: Exception) {
            false
        }
    }

    override suspend fun getTipoVisitTercMovEquipVisitTerc(): TypeVisitTerc {
        return movEquipVisitTercDatasourceSharedPreferences.getMovEquipVisitTerc().tipoVisitTercMovEquipVisitTerc!!
    }

    override suspend fun listMovEquipVisitTercOpen(): List<br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipVisitTerc> {
        return movEquipVisitTercDatasourceRoom.listMovEquipVisitTercOpen().map { it.modelRoomToMovEquipVisitTerc() }
    }

    override suspend fun listMovEquipVisitTercStarted(): List<br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipVisitTerc> {
        return movEquipVisitTercDatasourceRoom.listMovEquipVisitTercStarted().map { it.modelRoomToMovEquipVisitTerc() }
    }

    override suspend fun listMovEquipVisitTercSend(): List<br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipVisitTerc> {
        return movEquipVisitTercDatasourceRoom.listMovEquipVisitTercSend().map { it.modelRoomToMovEquipVisitTerc() }
    }

    override suspend fun listMovEquipVisitTercSent(): List<br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipVisitTerc> {
        return movEquipVisitTercDatasourceRoom.listMovEquipVisitTercSent().map { it.modelRoomToMovEquipVisitTerc() }
    }

    override suspend fun receiverSentMovEquipVisitTerc(movEquipList: List<br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipVisitTerc>): Boolean {
        for(movEquip in movEquipList){
            if(!movEquipVisitTercDatasourceRoom.updateStatusMovEquipVisitTercSent(movEquip.idMovEquipVisitTerc!!)) return false
        }
        return true
    }

    override suspend fun saveMovEquipVisitTerc(matricVigia: Long, idLocal: Long): Long {
        try {
            val movEquipVisitTerc = movEquipVisitTercDatasourceSharedPreferences.getMovEquipVisitTerc().modelSharedPreferencesToMovEquipVisitTerc()
            val movEquipVisitTercRoomModel = movEquipVisitTerc.entityToMovEquipVisitTercRoomModel(matricVigia, idLocal)
            if(!movEquipVisitTercDatasourceRoom.insertMovEquipVisitTercOpen(movEquipVisitTercRoomModel)) return 0L
            if(!movEquipVisitTercDatasourceSharedPreferences.clearMovEquipVisitTerc()) return 0L
            return movEquipVisitTercDatasourceRoom.lastIdMovStatusStarted()
        } catch (exception: Exception){
            return 0L
        }
    }

    override suspend fun saveMovEquipVisitTerc(
        matricVigia: Long,
        idLocal: Long,
        movEquipVisitTerc: br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipVisitTerc
    ): Long {
        try {
            val movEquipVisitTercRoomModel = movEquipVisitTerc.entityToMovEquipVisitTercRoomModel(matricVigia, idLocal)
            if(!movEquipVisitTercDatasourceRoom.insertMovEquipVisitTercClose(movEquipVisitTercRoomModel)) return 0L
            if(!movEquipVisitTercDatasourceSharedPreferences.clearMovEquipVisitTerc()) return 0L
            return movEquipVisitTercDatasourceRoom.lastIdMovStatusStarted()
        } catch (exception: Exception){
            return 0L
        }
    }

    override suspend fun sendMovEquipVisitTerc(
        movEquipList: List<br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipVisitTerc>,
        nroAparelho: Long
    ): Result<List<br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipVisitTerc>> {
        val result = movEquipVisitTercDatasourceWebService.sendMovEquipVisitTerc(movEquipList.map { it.entityToMovEquipVisitTercWebServiceModel(nroAparelho) }, nroAparelho)
        if(result.isSuccess) {
            return result.map { listMovEquip ->
                listMovEquip.map { movEquip ->
                    movEquip.modelWebServiceToMovEquipVisitTerc()
                }
            }
        }
        return Result.failure(Throwable(result.exceptionOrNull()))
    }

    override suspend fun setDestinoMovEquipVisitTerc(destino: String): Boolean {
        return try {
            movEquipVisitTercDatasourceSharedPreferences.setDestinoMovEquipVisitTerc(destino)
        } catch (exception: Exception){
            false
        }
    }

    override suspend fun setIdVisitTercMovEquipVisitTerc(idVisitTerc: Long): Boolean {
        return try {
            movEquipVisitTercDatasourceSharedPreferences.setIdVisitTercMovEquipVisitTerc(idVisitTerc)
        } catch (exception: Exception){
            false
        }
    }

    override suspend fun setObservMovEquipVisitTerc(observ: String?): Boolean {
        return try {
            movEquipVisitTercDatasourceSharedPreferences.setObservMovEquipVisitTerc(observ)
        } catch (exception: Exception){
            false
        }
    }

    override suspend fun setPlacaMovEquipVisitTerc(placa: String): Boolean {
        return try {
            movEquipVisitTercDatasourceSharedPreferences.setPlacaMovEquipVisitTerc(placa)
        } catch (exception: Exception){
            false
        }
    }

    override suspend fun setTipoVisitTercMovEquipVisitTerc(typeVisitTerc: TypeVisitTerc): Boolean {
        return try {
            movEquipVisitTercDatasourceSharedPreferences.setTipoVisitTercMovEquipVisitTerc(typeVisitTerc)
        } catch (exception: Exception){
            false
        }
    }

    override suspend fun setVeiculoMovEquipVisitTerc(veiculo: String): Boolean {
        return try {
            movEquipVisitTercDatasourceSharedPreferences.setVeiculoMovEquipVisitTerc(veiculo)
        } catch (exception: Exception){
            false
        }
    }

    override suspend fun startMovEquipVisitTerc(): Boolean {
        return try {
            movEquipVisitTercDatasourceSharedPreferences.startMovEquipVisitTerc()
        } catch (exception: Exception){
            false
        }
    }

    override suspend fun updateVeiculoMovEquipVisitTerc(
        veiculo: String,
        movEquipVisitTerc: br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipVisitTerc
    ): Boolean {
        return try {
            movEquipVisitTercDatasourceRoom.updateVeiculoMovEquipVisitTerc(
                veiculo,
                movEquipVisitTerc.entityToMovEquipVisitTercRoomModel(
                    movEquipVisitTerc.nroMatricVigiaMovEquipVisitTerc!!,
                    movEquipVisitTerc.idLocalMovEquipVisitTerc!!
                )
            )
        } catch (exception: Exception) {
            false
        }
    }

    override suspend fun updatePlacaMovEquipVisitTerc(
        placa: String,
        movEquipVisitTerc: br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipVisitTerc
    ): Boolean {
        return try {
            movEquipVisitTercDatasourceRoom.updatePlacaMovEquipVisitTerc(
                placa,
                movEquipVisitTerc.entityToMovEquipVisitTercRoomModel(
                    movEquipVisitTerc.nroMatricVigiaMovEquipVisitTerc!!,
                    movEquipVisitTerc.idLocalMovEquipVisitTerc!!
                )
            )
        } catch (exception: Exception) {
            false
        }
    }

    override suspend fun updateMotoristaMovEquipVisitTerc(
        idVisitTerc: Long,
        movEquipVisitTerc: br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipVisitTerc
    ): Boolean {
        return try {
            movEquipVisitTercDatasourceRoom.updateMotoristaMovEquipVisitTerc(
                idVisitTerc,
                movEquipVisitTerc.entityToMovEquipVisitTercRoomModel(
                    movEquipVisitTerc.nroMatricVigiaMovEquipVisitTerc!!,
                    movEquipVisitTerc.idLocalMovEquipVisitTerc!!
                )
            )
        } catch (exception: Exception) {
            false
        }
    }

    override suspend fun updateDestinoMovEquipVisitTerc(
        destino: String,
        movEquipVisitTerc: br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipVisitTerc
    ): Boolean {
        return try {
            movEquipVisitTercDatasourceRoom.updateDestinoMovEquipVisitTerc(
                destino,
                movEquipVisitTerc.entityToMovEquipVisitTercRoomModel(
                    movEquipVisitTerc.nroMatricVigiaMovEquipVisitTerc!!,
                    movEquipVisitTerc.idLocalMovEquipVisitTerc!!
                )
            )
        } catch (exception: Exception) {
            false
        }
    }

    override suspend fun updateObservMovEquipVisitTerc(
        observ: String?,
        movEquipVisitTerc: br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipVisitTerc
    ): Boolean {
        return try {
            movEquipVisitTercDatasourceRoom.updateObservMovEquipVisitTerc(
                observ,
                movEquipVisitTerc.entityToMovEquipVisitTercRoomModel(
                    movEquipVisitTerc.nroMatricVigiaMovEquipVisitTerc!!,
                    movEquipVisitTerc.idLocalMovEquipVisitTerc!!
                )
            )
        } catch (exception: Exception) {
            false
        }
    }

}