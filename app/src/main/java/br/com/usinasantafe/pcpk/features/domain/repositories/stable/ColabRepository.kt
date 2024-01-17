package br.com.usinasantafe.pcpk.features.domain.repositories.stable

import br.com.usinasantafe.pcpk.features.domain.entities.stable.Colab
import kotlinx.coroutines.flow.Flow

interface ColabRepository {

    suspend fun addAllColab(list: List<Colab>)

    suspend fun checkColabMatric(matric: Long): Boolean

    suspend fun deleteAllColab()

    suspend fun getColabMatric(matric: Long): Colab

    suspend fun recoverAllColab(nroAparelho: Long): Flow<Result<List<Colab>>>

}