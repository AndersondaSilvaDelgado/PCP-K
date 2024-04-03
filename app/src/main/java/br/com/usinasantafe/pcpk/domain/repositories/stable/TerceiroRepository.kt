package br.com.usinasantafe.pcpk.domain.repositories.stable

import br.com.usinasantafe.pcpk.domain.entities.stable.Terceiro
import br.com.usinasantafe.pcpk.domain.entities.stable.Visitante
import kotlinx.coroutines.flow.Flow

interface TerceiroRepository {

    suspend fun addAllTerceiro(list: List<br.com.usinasantafe.pcpk.domain.entities.stable.Terceiro>)

    suspend fun checkCPFTerceiro(cpf: String): Boolean

    suspend fun deleteAllTerceiro()

    suspend fun getTerceiroCPF(cpf: String): br.com.usinasantafe.pcpk.domain.entities.stable.Terceiro

    suspend fun getTerceiroListCPF(cpf: String): List<br.com.usinasantafe.pcpk.domain.entities.stable.Terceiro>

    suspend fun getTerceiroId(id: Long): br.com.usinasantafe.pcpk.domain.entities.stable.Terceiro

    suspend fun recoverAllTerceiro(nroAparelho: Long): Flow<Result<List<br.com.usinasantafe.pcpk.domain.entities.stable.Terceiro>>>

}