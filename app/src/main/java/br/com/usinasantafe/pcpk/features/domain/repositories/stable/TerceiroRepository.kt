package br.com.usinasantafe.pcpk.features.domain.repositories.stable

import br.com.usinasantafe.pcpk.features.domain.entities.stable.Terceiro
import kotlinx.coroutines.flow.Flow

interface TerceiroRepository {

    suspend fun addAllTerceiro(list: List<Terceiro>)

    suspend fun deleteAllTerceiro()

    suspend fun recoverAllTerceiro(nroAparelho: Long): Flow<Result<List<Terceiro>>>

}