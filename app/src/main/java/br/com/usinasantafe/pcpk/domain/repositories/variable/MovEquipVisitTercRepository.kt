package br.com.usinasantafe.pcpk.domain.repositories.variable

import br.com.usinasantafe.pcpk.utils.TypeVisitTerc
import br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipVisitTerc

interface MovEquipVisitTercRepository {

    suspend fun checkMovSend(): Boolean

    suspend fun deleteMovEquipVisitTerc(movEquipVisitTerc: br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipVisitTerc): Boolean

    suspend fun getTipoVisitTercMovEquipVisitTerc(): TypeVisitTerc

    suspend fun listMovEquipVisitTercOpen(): List<br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipVisitTerc>

    suspend fun listMovEquipVisitTercStarted(): List<br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipVisitTerc>

    suspend fun listMovEquipVisitTercSend(): List<br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipVisitTerc>

    suspend fun listMovEquipVisitTercSent(): List<br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipVisitTerc>

    suspend fun receiverSentMovEquipVisitTerc(movEquipList: List<br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipVisitTerc>): Boolean

    suspend fun saveMovEquipVisitTerc(matricVigia: Long, idLocal: Long): Long

    suspend fun saveMovEquipVisitTerc(matricVigia: Long, idLocal: Long, movEquipVisitTerc: br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipVisitTerc): Long

    suspend fun sendMovEquipVisitTerc(movEquipList: List<br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipVisitTerc>, nroAparelho: Long): Result<List<br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipVisitTerc>>

    suspend fun setDestinoMovEquipVisitTerc(destino: String): Boolean

    suspend fun setIdVisitTercMovEquipVisitTerc(idVisitTerc: Long): Boolean

    suspend fun setObservMovEquipVisitTerc(observ: String?): Boolean

    suspend fun setPlacaMovEquipVisitTerc(placa: String): Boolean

    suspend fun setTipoVisitTercMovEquipVisitTerc(typeVisitTerc: TypeVisitTerc): Boolean

    suspend fun setStatusCloseMov(movEquipVisitTerc: br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipVisitTerc): Boolean

    suspend fun setStatusSendMov(movEquipVisitTerc: br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipVisitTerc): Boolean

    suspend fun setVeiculoMovEquipVisitTerc(veiculo: String): Boolean

    suspend fun startMovEquipVisitTerc(): Boolean

    suspend fun updateVeiculoMovEquipVisitTerc(
        veiculo: String,
        movEquipVisitTerc: br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipVisitTerc
    ): Boolean

    suspend fun updatePlacaMovEquipVisitTerc(
        placa: String,
        movEquipVisitTerc: br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipVisitTerc
    ): Boolean

    suspend fun updateMotoristaMovEquipVisitTerc(
        idVisitTerc: Long,
        movEquipVisitTerc: br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipVisitTerc
    ): Boolean

    suspend fun updateDestinoMovEquipVisitTerc(
        destino: String,
        movEquipVisitTerc: br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipVisitTerc
    ): Boolean

    suspend fun updateObservMovEquipVisitTerc(
        observ: String?,
        movEquipVisitTerc: br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipVisitTerc
    ): Boolean

}