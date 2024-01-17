package br.com.usinasantafe.pcpk.features.domain.repositories.stable

import br.com.usinasantafe.pcpk.features.domain.entities.stable.Terceiro
import br.com.usinasantafe.pcpk.features.domain.entities.stable.Visitante
import kotlinx.coroutines.flow.Flow

interface TerceiroRepository {

    suspend fun addAllTerceiro(list: List<Terceiro>)

    suspend fun checkCPFTerceiro(cpf: String): Boolean

    suspend fun deleteAllTerceiro()

    suspend fun getTerceiroCPF(cpf: String): Terceiro

    suspend fun getTerceiroListCPF(cpf: String): List<Terceiro>

    suspend fun getTerceiroId(id: Long): Terceiro

    suspend fun recoverAllTerceiro(nroAparelho: Long): Flow<Result<List<Terceiro>>>

}