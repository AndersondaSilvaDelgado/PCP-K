package br.com.usinasantafe.pcpk.domain.repositories.stable

import br.com.usinasantafe.pcpk.domain.entities.stable.Colab
import kotlinx.coroutines.flow.Flow

interface ColabRepository {

    suspend fun addAllColab(list: List<br.com.usinasantafe.pcpk.domain.entities.stable.Colab>)

    suspend fun checkColabMatric(matric: Long): Boolean

    suspend fun deleteAllColab()

    suspend fun getColabMatric(matric: Long): br.com.usinasantafe.pcpk.domain.entities.stable.Colab

    suspend fun recoverAllColab(nroAparelho: Long): Flow<Result<List<br.com.usinasantafe.pcpk.domain.entities.stable.Colab>>>

}