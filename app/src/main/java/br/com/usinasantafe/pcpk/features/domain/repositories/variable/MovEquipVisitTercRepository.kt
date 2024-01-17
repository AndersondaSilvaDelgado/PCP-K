package br.com.usinasantafe.pcpk.features.domain.repositories.variable

import br.com.usinasantafe.pcpk.common.utils.TypeVisitTerc
import br.com.usinasantafe.pcpk.features.domain.entities.variable.MovEquipVisitTerc

interface MovEquipVisitTercRepository {

    suspend fun checkMovSend(): Boolean

    suspend fun getTipoVisitTercMovEquipVisitTerc(): TypeVisitTerc

    suspend fun listMovEquipVisitTercOpen(): List<MovEquipVisitTerc>

    suspend fun listMovEquipVisitTercEmpty(): List<MovEquipVisitTerc>

    suspend fun listMovEquipVisitTercSend(): List<MovEquipVisitTerc>

    suspend fun receiverSentMovEquipVisitTerc(movEquipList: List<MovEquipVisitTerc>): Boolean

    suspend fun saveMovEquipVisitTerc(matricVigia: Long, idLocal: Long): Int

    suspend fun saveMovEquipVisitTerc(matricVigia: Long, idLocal: Long, movEquipVisitTerc: MovEquipVisitTerc): Int

    suspend fun sendMovEquipVisitTerc(movEquipList: List<MovEquipVisitTerc>, nroAparelho: Long): Result<List<MovEquipVisitTerc>>

    suspend fun setDestinoMovEquipVisitTerc(destino: String): Boolean

    suspend fun setIdVisitTercMovEquipVisitTerc(idVisitTerc: Long): Boolean

    suspend fun setObservMovEquipVisitTerc(observ: String?): Boolean

    suspend fun setPlacaMovEquipVisitTerc(placa: String): Boolean

    suspend fun setTipoVisitTercMovEquipVisitTerc(typeVisitTerc: TypeVisitTerc): Boolean

    suspend fun setStatusClosedMov(movEquipVisitTerc: MovEquipVisitTerc): Boolean

    suspend fun setStatusSendClosedMov(movEquipVisitTerc: MovEquipVisitTerc): Boolean

    suspend fun setVeiculoMovEquipVisitTerc(veiculo: String): Boolean

    suspend fun startMovEquipVisitTerc(): Boolean

}