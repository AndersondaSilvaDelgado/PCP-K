package br.com.usinasantafe.pcpk.domain.repositories.stable

import br.com.usinasantafe.pcpk.domain.entities.stable.Local
import kotlinx.coroutines.flow.Flow

interface LocalRepository {

    suspend fun addAllLocal(list: List<br.com.usinasantafe.pcpk.domain.entities.stable.Local>)

    suspend fun deleteAllLocal()

    suspend fun getLocalId(id: Long): br.com.usinasantafe.pcpk.domain.entities.stable.Local

    suspend fun listAllLocal(): List<br.com.usinasantafe.pcpk.domain.entities.stable.Local>

    suspend fun recoverAllLocal(nroAparelho: Long): Flow<Result<List<br.com.usinasantafe.pcpk.domain.entities.stable.Local>>>

}