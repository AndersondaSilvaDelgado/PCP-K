package br.com.usinasantafe.pcp.domain.repositories.stable

import br.com.usinasantafe.pcp.domain.entities.stable.Colab
import kotlinx.coroutines.flow.Flow

interface ColabRepository {

    suspend fun addAllColab(list: List<Colab>)

    suspend fun checkColabMatric(matric: Long): Boolean

    suspend fun deleteAllColab()

    suspend fun getColabMatric(matric: Long): Colab

    suspend fun recoverAllColab(token: String): Flow<Result<List<Colab>>>

}