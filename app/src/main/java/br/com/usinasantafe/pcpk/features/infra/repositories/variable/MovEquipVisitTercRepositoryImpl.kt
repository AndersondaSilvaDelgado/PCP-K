package br.com.usinasantafe.pcpk.features.infra.repositories.variable

import br.com.usinasantafe.pcpk.common.utils.TypeVisitTerc
import br.com.usinasantafe.pcpk.features.domain.entities.variable.MovEquipVisitTerc
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipVisitTercRepository
import br.com.usinasantafe.pcpk.features.infra.datasource.room.variable.MovEquipVisitTercDatasourceRoom
import br.com.usinasantafe.pcpk.features.infra.datasource.sharedpreferences.MovEquipVisitTercDatasourceSharedPreferences
import br.com.usinasantafe.pcpk.features.infra.datasource.webservice.variable.MovEquipVisitTercDatasourceWebService
import br.com.usinasantafe.pcpk.features.infra.models.room.variable.entityToMovEquipVisitTercRoomModel
import br.com.usinasantafe.pcpk.features.infra.models.room.variable.modelRoomToMovEquipVisitTerc
import br.com.usinasantafe.pcpk.features.infra.models.sharedpreferences.modelSharedPreferencesToMovEquipVisitTerc
import br.com.usinasantafe.pcpk.features.infra.models.webservice.entityToMovEquipVisitTercWebServiceModel
import br.com.usinasantafe.pcpk.features.infra.models.webservice.modelWebServiceToMovEquipVisitTerc
import javax.inject.Inject

class MovEquipVisitTercRepositoryImpl @Inject constructor (
    private val movEquipVisitTercDatasourceRoom: MovEquipVisitTercDatasourceRoom,
    private val movEquipVisitTercDatasourceSharedPreferences: MovEquipVisitTercDatasourceSharedPreferences,
    private val movEquipVisitTercDatasourceWebService: MovEquipVisitTercDatasourceWebService,
) : MovEquipVisitTercRepository {

    override suspend fun checkMovSend(): Boolean {
        return movEquipVisitTercDatasourceRoom.checkMovSend()
    }

    override suspend fun setStatusCloseMov(movEquipVisitTerc: MovEquipVisitTerc): Boolean {
        return try {
            movEquipVisitTercDatasourceRoom.updateStatusMovEquipVisitTercClose(
                movEquipVisitTerc.entityToMovEquipVisitTercRoomModel(
                    movEquipVisitTerc.nroMatricVigiaMovEquipVisitTerc!!,
                    movEquipVisitTerc.idLocalMovEquipVisitTerc!!
                )
            )
        } catch (exception: Exception) {
            false
        }
    }

    override suspend fun setStatusSendCloseMov(movEquipVisitTerc: MovEquipVisitTerc): Boolean {
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

    override suspend fun listMovEquipVisitTercOpen(): List<MovEquipVisitTerc> {
        return movEquipVisitTercDatasourceRoom.listMovEquipVisitTercOpen().map { it.modelRoomToMovEquipVisitTerc() }
    }

    override suspend fun listMovEquipVisitTercStarted(): List<MovEquipVisitTerc> {
        return movEquipVisitTercDatasourceRoom.listMovEquipVisitTercStarted().map { it.modelRoomToMovEquipVisitTerc() }
    }

    override suspend fun listMovEquipVisitTercSend(): List<MovEquipVisitTerc> {
        return movEquipVisitTercDatasourceRoom.listMovEquipVisitTercSend().map { it.modelRoomToMovEquipVisitTerc() }
    }

    override suspend fun receiverSentMovEquipVisitTerc(movEquipList: List<MovEquipVisitTerc>): Boolean {
        for(movEquip in movEquipList){
            if(!movEquipVisitTercDatasourceRoom.updateStatusMovEquipVisitTercSent(movEquip.idMovEquipVisitTerc!!)) return false
        }
        return true
    }

    override suspend fun saveMovEquipVisitTerc(matricVigia: Long, idLocal: Long): Int {
        try {
            val movEquipVisitTerc = movEquipVisitTercDatasourceSharedPreferences.getMovEquipVisitTerc().modelSharedPreferencesToMovEquipVisitTerc()
            val movEquipVisitTercRoomModel = movEquipVisitTerc.entityToMovEquipVisitTercRoomModel(matricVigia, idLocal)
            if(!movEquipVisitTercDatasourceRoom.insertMovEquipVisitTercOpen(movEquipVisitTercRoomModel)) return 0
            if(!movEquipVisitTercDatasourceSharedPreferences.clearMovEquipVisitTerc()) return 0
            return movEquipVisitTercDatasourceRoom.lastIdMovStatusSend()
        } catch (exception: Exception){
            return 0
        }
    }

    override suspend fun saveMovEquipVisitTerc(
        matricVigia: Long,
        idLocal: Long,
        movEquipVisitTerc: MovEquipVisitTerc
    ): Int {
        val movEquipVisitTercRoomModel = movEquipVisitTerc.entityToMovEquipVisitTercRoomModel(matricVigia, idLocal)
        if(!movEquipVisitTercDatasourceRoom.insertMovEquipVisitTercClose(movEquipVisitTercRoomModel)) return 0
        if(!movEquipVisitTercDatasourceSharedPreferences.clearMovEquipVisitTerc()) return 0
        return movEquipVisitTercDatasourceRoom.lastIdMovStatusSend()
    }

    override suspend fun sendMovEquipVisitTerc(
        movEquipList: List<MovEquipVisitTerc>,
        nroAparelho: Long
    ): Result<List<MovEquipVisitTerc>> {
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
        movEquipVisitTerc: MovEquipVisitTerc
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
        movEquipVisitTerc: MovEquipVisitTerc
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
        movEquipVisitTerc: MovEquipVisitTerc
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
        movEquipVisitTerc: MovEquipVisitTerc
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
        movEquipVisitTerc: MovEquipVisitTerc
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