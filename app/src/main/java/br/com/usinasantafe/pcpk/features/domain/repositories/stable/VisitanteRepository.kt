package br.com.usinasantafe.pcpk.features.domain.repositories.stable

import br.com.usinasantafe.pcpk.features.domain.entities.stable.Visitante
import kotlinx.coroutines.flow.Flow

interface VisitanteRepository {

    suspend fun addAllVisitante(list: List<Visitante>)

    suspend fun deleteAllVisitante()

    suspend fun recoverAllVisitante(nroAparelho: Long): Flow<Result<List<Visitante>>>

}