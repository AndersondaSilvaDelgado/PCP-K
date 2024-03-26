package br.com.usinasantafe.pcpk.features.infra.repositories.variable

import android.util.Log
import br.com.usinasantafe.pcpk.common.utils.TypeMov
import br.com.usinasantafe.pcpk.features.domain.entities.variable.MovEquipProprio
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioRepository
import br.com.usinasantafe.pcpk.features.infra.datasource.room.variable.MovEquipProprioDatasourceRoom
import br.com.usinasantafe.pcpk.features.infra.datasource.sharedpreferences.MovEquipProprioDatasourceSharedPreferences
import br.com.usinasantafe.pcpk.features.infra.datasource.webservice.variable.MovEquipProprioDatasourceWebService
import br.com.usinasantafe.pcpk.features.infra.models.room.variable.modelRoomToMovEquipProprio
import br.com.usinasantafe.pcpk.features.infra.models.room.variable.entityToMovEquipProprioRoomModel
import br.com.usinasantafe.pcpk.features.infra.models.sharedpreferences.modelSharedPreferencesToMovEquipProprio
import br.com.usinasantafe.pcpk.features.infra.models.webservice.entityToMovEquipProprioWebServiceModel
import br.com.usinasantafe.pcpk.features.infra.models.webservice.modelWebServiceToMovEquipProprio
import javax.inject.Inject

class MovEquipProprioRepositoryImpl @Inject constructor(
    private val movEquipProprioDatasourceRoom: MovEquipProprioDatasourceRoom,
    private val movEquipProprioDatasourceSharedPreferences: MovEquipProprioDatasourceSharedPreferences,
    private val movEquipProprioDatasourceWebService: MovEquipProprioDatasourceWebService,
) : MovEquipProprioRepository {

    override suspend fun checkAddMotoristaMovEquipProprio(): Boolean {
        val movEquipProprio = movEquipProprioDatasourceSharedPreferences.getMovEquipProprio()
        return movEquipProprio.nroMatricColabMovEquipProprio == null
    }

    override suspend fun checkAddVeiculoMovEquipProprio(): Boolean {
        val movEquipProprio = movEquipProprioDatasourceSharedPreferences.getMovEquipProprio()
        return movEquipProprio.idEquipMovEquipProprio == null
    }

    override suspend fun checkMovSend(): Boolean {
        return movEquipProprioDatasourceRoom.checkMovSend()
    }

    override suspend fun deleteMovEquipProprio(movEquipProprio: MovEquipProprio): Boolean {
        return try {
            movEquipProprioDatasourceRoom.deleteMov(
                movEquipProprio.entityToMovEquipProprioRoomModel(
                    movEquipProprio.nroMatricVigiaMovEquipProprio!!,
                    movEquipProprio.idLocalMovEquipProprio!!
                )
            )
        } catch (exception: Exception) {
            false
        }
    }

    override suspend fun setStatusSendMov(movEquipProprio: MovEquipProprio): Boolean {
        return try {
            movEquipProprioDatasourceRoom.updateStatusMovEquipProprioCloseSend(
                movEquipProprio.entityToMovEquipProprioRoomModel(
                    movEquipProprio.nroMatricVigiaMovEquipProprio!!,
                    movEquipProprio.idLocalMovEquipProprio!!
                )
            )
        } catch (exception: Exception) {
            false
        }
    }

    override suspend fun getMatricMotoristaMovEquipProprio(): Long {
        return movEquipProprioDatasourceSharedPreferences.getMovEquipProprio().nroMatricColabMovEquipProprio!!
    }

    override suspend fun getTipoMovEquipProprio(): TypeMov {
        return movEquipProprioDatasourceSharedPreferences.getMovEquipProprio().tipoMovEquipProprio
    }

    override suspend fun listMovEquipProprioStarted(): List<MovEquipProprio> {
        return movEquipProprioDatasourceRoom.listMovEquipProprioStarted()
            .map { it.modelRoomToMovEquipProprio() }
    }

    override suspend fun listMovEquipProprioSend(): List<MovEquipProprio> {
        return movEquipProprioDatasourceRoom.listMovEquipProprioSend()
            .map { it.modelRoomToMovEquipProprio() }
    }

    override suspend fun listMovEquipProprioSent(): List<MovEquipProprio> {
        return movEquipProprioDatasourceRoom.listMovEquipProprioSent()
            .map { it.modelRoomToMovEquipProprio() }
    }

    override suspend fun receiverSentMovEquipProprio(movEquipList: List<MovEquipProprio>): Boolean {
        for (movEquipProprio in movEquipList) {
            if (!movEquipProprioDatasourceRoom.updateStatusMovEquipProprioSent(movEquipProprio.idMovEquipProprio!!)) return false
        }
        return true
    }

    override suspend fun saveMovEquipProprio(matricVigia: Long, idLocal: Long): Long {
        try {
            val movEquipProprio = movEquipProprioDatasourceSharedPreferences.getMovEquipProprio()
                .modelSharedPreferencesToMovEquipProprio()
            val movEquipProprioRoomModel =
                movEquipProprio.entityToMovEquipProprioRoomModel(matricVigia, idLocal)
            if (!movEquipProprioDatasourceRoom.saveMovEquipProprioOpen(movEquipProprioRoomModel)) return 0L
            if (!movEquipProprioDatasourceSharedPreferences.clearMovEquipProprio()) return 0L
            return movEquipProprioDatasourceRoom.lastIdMovStatusStarted()
        } catch (exception: Exception) {
            return 0
        }
    }

    override suspend fun sendMovEquipProprio(
        movEquipList: List<MovEquipProprio>,
        nroAparelho: Long
    ): Result<List<MovEquipProprio>> {
        val result = movEquipProprioDatasourceWebService.sendMovEquipProprio(movEquipList.map {
            it.entityToMovEquipProprioWebServiceModel(nroAparelho)
        }, nroAparelho)
        if (result.isSuccess) {
            return result.map { listMovEquip ->
                listMovEquip.map { movEquip ->
                    movEquip.modelWebServiceToMovEquipProprio()
                }
            }
        }
        return Result.failure(Throwable(result.exceptionOrNull()))
    }

    override suspend fun setDestinoMovEquipProprio(destino: String): Boolean {
        return try {
            movEquipProprioDatasourceSharedPreferences.setDestinoMovEquipProprio(destino)
        } catch (exception: Exception) {
            false
        }
    }

    override suspend fun setMotoristaMovEquipProprio(nroMatric: Long): Boolean {
        return try {
            movEquipProprioDatasourceSharedPreferences.setMotoristaMovEquipProprio(nroMatric)
        } catch (exception: Exception) {
            false
        }
    }

    override suspend fun setNotaFiscalMovEquipProprio(notaFiscal: Long): Boolean {
        return try {
            movEquipProprioDatasourceSharedPreferences.setNotaFiscalMovEquipProprio(notaFiscal)
        } catch (exception: Exception) {
            false
        }
    }

    override suspend fun setObservMovEquipProprio(observ: String?): Boolean {
        return try {
            movEquipProprioDatasourceSharedPreferences.setObservMovEquipProprio(observ)
        } catch (exception: Exception) {
            false
        }
    }

    override suspend fun setVeiculoMovEquipProprio(idEquip: Long): Boolean {
        return try {
            movEquipProprioDatasourceSharedPreferences.setVeiculoMovEquipProprio(idEquip)
        } catch (exception: Exception) {
            false
        }
    }

    override suspend fun startMovEquipProprio(typeMov: TypeMov): Boolean {
        return try {
            movEquipProprioDatasourceSharedPreferences.startMovEquipProprio(typeMov)
        } catch (exception: Exception) {
            false
        }
    }

    override suspend fun updateDestinoMovEquipProprio(
        destino: String,
        movEquipProprio: MovEquipProprio
    ): Boolean {
        return try {
            movEquipProprioDatasourceRoom.updateDestinoMovEquipProprio(
                destino,
                movEquipProprio.entityToMovEquipProprioRoomModel(
                    movEquipProprio.nroMatricVigiaMovEquipProprio!!,
                    movEquipProprio.idLocalMovEquipProprio!!
                )
            )
        } catch (exception: Exception) {
            false
        }
    }

    override suspend fun updateMotoristaMovEquipProprio(
        nroMatric: Long,
        movEquipProprio: MovEquipProprio
    ): Boolean {
        return try {
            movEquipProprioDatasourceRoom.updateNroColabMovEquipProprio(
                nroMatric,
                movEquipProprio.entityToMovEquipProprioRoomModel(
                    movEquipProprio.nroMatricVigiaMovEquipProprio!!,
                    movEquipProprio.idLocalMovEquipProprio!!
                )
            )
        } catch (exception: Exception) {
            false
        }
    }

    override suspend fun updateNotaFiscalMovEquipProprio(
        notaFiscal: Long,
        movEquipProprio: MovEquipProprio
    ): Boolean {
        return try {
            movEquipProprioDatasourceRoom.updateNotaFiscalMovEquipProprio(
                notaFiscal,
                movEquipProprio.entityToMovEquipProprioRoomModel(
                    movEquipProprio.nroMatricVigiaMovEquipProprio!!,
                    movEquipProprio.idLocalMovEquipProprio!!
                )
            )
        } catch (exception: Exception) {
            false
        }
    }

    override suspend fun updateVeiculoMovEquipProprio(
        idEquip: Long,
        movEquipProprio: MovEquipProprio
    ): Boolean {
        return try {
            movEquipProprioDatasourceRoom.updateIdEquipMovEquipProprio(
                idEquip,
                movEquipProprio.entityToMovEquipProprioRoomModel(
                    movEquipProprio.nroMatricVigiaMovEquipProprio!!,
                    movEquipProprio.idLocalMovEquipProprio!!
                )
            )
        } catch (exception: Exception) {
            false
        }
    }

    override suspend fun updateObservMovEquipProprio(
        observ: String?,
        movEquipProprio: MovEquipProprio
    ): Boolean {
        return try {
            movEquipProprioDatasourceRoom.updateObservMovEquipProprio(
                observ,
                movEquipProprio.entityToMovEquipProprioRoomModel(
                    movEquipProprio.nroMatricVigiaMovEquipProprio!!,
                    movEquipProprio.idLocalMovEquipProprio!!
                )
            )
        } catch (exception: Exception) {
            false
        }
    }

}