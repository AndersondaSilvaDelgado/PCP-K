package br.com.usinasantafe.pcp.infra.repositories.stable

import br.com.usinasantafe.pcp.domain.repositories.stable.ColabRepository
import br.com.usinasantafe.pcp.infra.datasource.room.stable.ColabRoomDatasource
import br.com.usinasantafe.pcp.infra.datasource.webservice.stable.ColabDatasourceWebService
import br.com.usinasantafe.pcp.infra.models.room.stable.toColab
import br.com.usinasantafe.pcp.infra.models.room.stable.toColabModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ColabRepositoryImpl @Inject constructor(
    private val colabRoomDatasource: ColabRoomDatasource,
    private val colabDatasourceWebService: ColabDatasourceWebService,
): ColabRepository {

    override suspend fun addAllColab(list: List<br.com.usinasantafe.pcp.domain.entities.stable.Colab>) {
        colabRoomDatasource.addAllColab(*list.map { it.toColabModel() }.toTypedArray())
    }

    override suspend fun deleteAllColab() {
        colabRoomDatasource.deleteAllColab()
    }

    override suspend fun recoverAllColab(token: String): Flow<Result<List<br.com.usinasantafe.pcp.domain.entities.stable.Colab>>> = flow {
        colabDatasourceWebService.getAllColab(token)
            .catch { exception -> emit(Result.failure(exception)) }
            .collect { result ->
                result.fold(
                    onSuccess = { colabModelList ->
                        emit(Result.success(colabModelList.map { it.toColab() }))
                    },
                    onFailure = { exception -> emit(Result.failure(exception)) }
                )
            }
    }

    override suspend fun checkColabMatric(matric: Long): Boolean {
        return colabRoomDatasource.checkColabMatric(matric)
    }

    override suspend fun getColabMatric(matric: Long): br.com.usinasantafe.pcp.domain.entities.stable.Colab {
        return colabRoomDatasource.getColabMatric(matric).toColab()
    }

}