package br.com.usinasantafe.pcpk.domain.repositories.stable

import br.com.usinasantafe.pcpk.domain.entities.stable.Visitante
import kotlinx.coroutines.flow.Flow

interface VisitanteRepository {

    suspend fun addAllVisitante(list: List<br.com.usinasantafe.pcpk.domain.entities.stable.Visitante>)

    suspend fun checkCPFVisitante(cpf: String): Boolean

    suspend fun deleteAllVisitante()

    suspend fun getVisitanteCPF(cpf: String): br.com.usinasantafe.pcpk.domain.entities.stable.Visitante

    suspend fun getVisitanteId(id: Long): br.com.usinasantafe.pcpk.domain.entities.stable.Visitante

    suspend fun recoverAllVisitante(nroAparelho: Long): Flow<Result<List<br.com.usinasantafe.pcpk.domain.entities.stable.Visitante>>>

}